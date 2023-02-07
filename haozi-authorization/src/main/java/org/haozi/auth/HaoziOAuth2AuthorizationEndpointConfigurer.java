package org.haozi.auth;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.haozi.auth.endpoint.provider.AuthProvider;
import org.haozi.auth.token.HaoZiToken;
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

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/6 19:27
 */
public class HaoziOAuth2AuthorizationEndpointConfigurer {

    class HaoZiAuthenticationConverter implements AuthenticationConverter {
        private String GRAN_TYPE = "grant_type";
        private static final String DEFAULT_ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-4.1.2.1";

        @Override
        public Authentication convert(HttpServletRequest request) {

            MultiValueMap<String, String> parameters = HaoZiOAuth2EndpointUtils.getParameters(request);

            String granType = parameters.getFirst(GRAN_TYPE);

            Map<String, AuthProvider> authProviders = SpringUtil.getBeansOfType(AuthProvider.class);

            if (CollUtil.isEmpty(authProviders)) {
                return null;
            }
            AtomicReference<Authentication> authentication = null;
            authProviders.forEach((class0, provider) -> {

                if (provider.isSupport(granType)) {

                    String clientId = parameters.getFirst(OAuth2ParameterNames.CLIENT_ID);
                    if (!StringUtils.hasText(clientId) ||
                            parameters.get(OAuth2ParameterNames.CLIENT_ID).size() != 1) {
                        throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.CLIENT_ID, DEFAULT_ERROR_URI);
                    }

                    authentication.set(provider.convert(request, parameters));
                }

            });


            if (authentication != null) {
                return authentication.get();
            }

            return null;
        }

        private static void throwError(String errorCode, String parameterName, String errorUri) {
            OAuth2Error error = new OAuth2Error(errorCode, "OAuth 2.0 Parameter: " + parameterName, errorUri);
            throw new OAuth2AuthorizationCodeRequestAuthenticationException(error, null);
        }
    }

    class HaoZiAuthenticationProvider implements AuthenticationProvider {

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {


            return null;
        }

        @Override
        public boolean supports(Class<?> authentication) {
            return HaoZiToken.class.isAssignableFrom(authentication);
        }
    }
}
