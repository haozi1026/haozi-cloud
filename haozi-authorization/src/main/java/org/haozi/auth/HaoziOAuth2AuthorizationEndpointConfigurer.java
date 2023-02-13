package org.haozi.auth;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.haozi.auth.endpoint.provider.AuthProvider;
import org.haozi.auth.token.HaoZiToken;
import org.haozi.exception.AppFramworkException;
import org.haozi.exception.AuthException;
import org.haozi.util.HaoZiOAuth2EndpointUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;

import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationException;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/6 19:27
 */
@Slf4j
public class HaoziOAuth2AuthorizationEndpointConfigurer {

    /**
     * 自定义 转换器
     */
    public static class HaoZiAuthenticationConverter implements AuthenticationConverter {
        private String GRAN_TYPE = "grant_type";
        private final RequestMatcher tokenEndpointMatcher = new AntPathRequestMatcher("/oauth2/token", HttpMethod.POST.name());

        @Override
        public Authentication convert(HttpServletRequest request) {

            if (!tokenEndpointMatcher.matches(request)) {
                return null;
            }

            MultiValueMap<String, String> parameters = HaoZiOAuth2EndpointUtils.getParameters(request);

            String granType = parameters.getFirst(GRAN_TYPE);
            Map<String, AuthProvider> authProviders = SpringUtil.getBeansOfType(AuthProvider.class);

            if (CollUtil.isEmpty(authProviders)) {
                return null;
            }
            log.trace("自定义处理器匹配，认证类型{},处理器{}", granType, authProviders);

            AtomicReference<HaoZiToken> authentication = new AtomicReference<>();
            authProviders.forEach((class0, provider) -> {

                if (provider.isSupport(granType)) {

                    String clientId = parameters.getFirst(OAuth2ParameterNames.CLIENT_ID);

                    if (!StringUtils.hasText(clientId) ||
                            parameters.get(OAuth2ParameterNames.CLIENT_ID).size() != 1) {
                        throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.CLIENT_ID, DEFAULT_ERROR_URI);
                    }
                    RegisteredClientRepository registeredClientRepository = SpringUtil.getBean(RegisteredClientRepository.class);
                    RegisteredClient registeredClient = registeredClientRepository.findByClientId(clientId);
                    if (registeredClient == null) {
                        throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.CLIENT_ID, DEFAULT_ERROR_URI);

                    }
                    HaoZiToken authenticationConvert = null;
                    try {
                        authenticationConvert = provider.convert(request, parameters);
                        authenticationConvert.setRegisteredClient(registeredClient);

                    } catch (AuthException exception) {
                        throwError(OAuth2ErrorCodes.INVALID_REQUEST, exception.getMessage(), DEFAULT_ERROR_URI);
                    }
                    if (authenticationConvert != null) {
                        authProviderCache.put(authenticationConvert.getClass(), provider);
                    }

                    authentication.set(authenticationConvert);
                }

            });


            if (authentication != null) {
                return authentication.get();
            }

            return null;
        }


    }

    /**
     * 自定义认证
     */
    public static class HaoZiAuthenticationProvider implements AuthenticationProvider {
        private OAuth2AuthorizationService oAuth2AuthorizationService;

        //       private  OAuth2TokenGenerator oAuth2TokenGenerator;
//        public HaoZiAuthenticationProvider(OAuth2AuthorizationService oAuth2AuthorizationService,OAuth2TokenGenerator tokenGenerator){
//            this.oAuth2AuthorizationService = oAuth2AuthorizationService;
//            this.oAuth2TokenGenerator = tokenGenerator;
//        }
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {

            AuthProvider authProvider = authProviderCache.get(authentication.getClass());
            HaoZiToken haoZiToken = (HaoZiToken) authentication;
            if (authProvider == null) {
                throw new AppFramworkException("未获取到认证处理器");
            }
            Authentication auth = null;
            try {
                auth = authProvider.auth(haoZiToken);
            } catch (AuthException exception) {
                throwError(OAuth2ErrorCodes.INVALID_REQUEST, exception.getMessage(), DEFAULT_ERROR_URI);
            }

            RegisteredClient registeredClient = haoZiToken.getRegisteredClient();
            DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
                    .registeredClient(registeredClient)
                    .principal(authentication)
                    .authorizationServerContext(AuthorizationServerContextHolder.getContext());

            Set<String> scopes = new HashSet<>();

            if (CollUtil.isNotEmpty(auth.getAuthorities())) {
                scopes = auth.getAuthorities().stream().map(authorite -> authorite.getAuthority()).collect(Collectors.toSet());
            }

            OAuth2TokenContext tokenContext = tokenContextBuilder
                    .tokenType(OAuth2TokenType.ACCESS_TOKEN)
                    .authorizedScopes(scopes).build();
            OAuth2Token generatedAccessToken = SpringUtil.getBean(OAuth2TokenGenerator.class).generate(tokenContext);
            if (generatedAccessToken == null) {
                OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
                        "The token generator failed to generate the access token.", "");
                throw new OAuth2AuthenticationException(error);
            }

            OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                    generatedAccessToken.getTokenValue(), generatedAccessToken.getIssuedAt(),
                    generatedAccessToken.getExpiresAt(), tokenContext.getAuthorizedScopes());


            tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build();
            OAuth2Token generatedRefreshToken = SpringUtil.getBean(OAuth2TokenGenerator.class).generate(tokenContext);
            if (!(generatedRefreshToken instanceof OAuth2RefreshToken)) {
                OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
                        "The token generator failed to generate the refresh token.", "");
                throw new OAuth2AuthenticationException(error);
            }


            Map<String, Object> additionalParameters = Collections.emptyMap();


            return new OAuth2AccessTokenAuthenticationToken(
                    registeredClient, authentication, accessToken, (OAuth2RefreshToken) generatedRefreshToken, additionalParameters);
        }

        @Override
        public boolean supports(Class<?> authentication) {
            return HaoZiToken.class.isAssignableFrom(authentication);
        }
    }

    private static void throwError(String errorCode, String desc, String errorUri) {
        OAuth2Error error = new OAuth2Error(errorCode, "OAuth 2.0 error: " + desc, errorUri);
        throw new OAuth2AuthorizationCodeRequestAuthenticationException(error, null);
    }

    private static final String DEFAULT_ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-4.1.2.1";

    private static Map<Class<? extends HaoZiToken>, AuthProvider>
            authProviderCache = new HashMap<>();
}
