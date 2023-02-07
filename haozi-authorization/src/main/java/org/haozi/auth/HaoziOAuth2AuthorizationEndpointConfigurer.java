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
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationException;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

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

        @Override
        public Authentication convert(HttpServletRequest request) {

            MultiValueMap<String, String> parameters = HaoZiOAuth2EndpointUtils.getParameters(request);

            String granType = parameters.getFirst(GRAN_TYPE);
            Map<String, AuthProvider> authProviders = SpringUtil.getBeansOfType(AuthProvider.class);

            if (CollUtil.isEmpty(authProviders)) {
                return null;
            }
            log.trace("自定义处理器匹配，认证类型{},处理器{}", granType, authProviders);

            AtomicReference<Authentication> authentication = new AtomicReference<>();
            authProviders.forEach((class0, provider) -> {

                if (provider.isSupport(granType)) {

                    String clientId = parameters.getFirst(OAuth2ParameterNames.CLIENT_ID);

                    if (!StringUtils.hasText(clientId) ||
                            parameters.get(OAuth2ParameterNames.CLIENT_ID).size() != 1) {
                        throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.CLIENT_ID, DEFAULT_ERROR_URI);
                    }
                    HaoZiToken authenticationConvert = null;
                    try {
                        authenticationConvert = provider.convert(request, parameters);

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

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {

            AuthProvider authProvider = authProviderCache.get(authentication.getClass());

            if (authProvider == null) {
                throw new AppFramworkException("未获取到认证处理器");
            }
            try {
                authProvider.auth((HaoZiToken) authentication);

            } catch (AuthException exception) {
                throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.CLIENT_ID, DEFAULT_ERROR_URI);
            }
            return null;
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
