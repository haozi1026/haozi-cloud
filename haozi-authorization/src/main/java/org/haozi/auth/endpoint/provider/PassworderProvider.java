package org.haozi.auth.endpoint.provider;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.haozi.auth.authentication.password.HaoZiUserDetailsAuthenticationProvider;
import org.haozi.auth.token.HaoZiToken;
import org.haozi.auth.token.UsernamePasswordToken;
import org.haozi.exception.AppFramworkException;
import org.haozi.exception.AuthException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/6 19:55
 */
public class PassworderProvider implements AuthProvider {
    private String PASSWORD = "password";
    private String USERNAME = "username";

    @Override
    public boolean isSupport(String granType) {
        return PASSWORD.equals(granType);
    }

    @Override
    public HaoZiToken convert(HttpServletRequest request, MultiValueMap<String, String> parameters) {


        String password = parameters.getFirst(PASSWORD);
        String userName = parameters.getFirst(USERNAME);


        if (StrUtil.isBlank(userName)) {

            throw new AuthException("userName为空");
        }
        if (StrUtil.isBlank(password)) {
            throw new AuthException("password为空");
        }

        return new UsernamePasswordToken(userName, password);
    }

    @Override
    public Authentication auth(HaoZiToken token) {

        if (token instanceof UsernamePasswordToken == false) {
            throw new AppFramworkException("密码模式校验异常，传入token类型错误");
        }
        String principal = Convert.toStr(token.getPrincipal());
        String credentials = Convert.toStr(token.getCredentials());

        if (StrUtil.isBlank(principal) || StrUtil.isBlank(credentials)) {
            throw new AppFramworkException("密码模式校验异常，传入token错误，用户名或密码为空");
        }
        Map<String, HaoZiUserDetailsAuthenticationProvider> userDetailsAuthenticationProvider = SpringUtil.getBeansOfType(HaoZiUserDetailsAuthenticationProvider.class);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(token.getPrincipal(), token.getCredentials());

        Set<String> keys = userDetailsAuthenticationProvider.keySet();
        for (String key : keys) {
            //只选取第一个
            return userDetailsAuthenticationProvider.get(key).authenticate(usernamePasswordAuthenticationToken);
        }
        return null;
    }
}
