package org.haozi.auth.endpoint.provider;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.haozi.auth.token.HaoZiToken;
import org.haozi.auth.token.UsernamePasswordToken;
import org.haozi.exception.AppFramworkException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

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
    public Authentication convert(HttpServletRequest request, MultiValueMap<String, String> parameters) {


        String password = parameters.getFirst(PASSWORD);
        String userName = parameters.getFirst(USERNAME);

        return new UsernamePasswordToken(userName, password);
    }

    @Override
    public Authentication auth(HaoZiToken token) {
        if (token instanceof UsernamePasswordToken == false) {
            throw new AppFramworkException("密码模式校验异常，传入token类型错误");
        }
        String principal = StrUtil.toString(token.getPrincipal());
        String credentials = StrUtil.toString(token.getCredentials());

        if (StrUtil.isBlank(principal) || StrUtil.isBlank(credentials)) {
            throw new AppFramworkException("密码模式校验异常，传入token错误，用户名或密码为空");
        }

        return null;
    }
}
