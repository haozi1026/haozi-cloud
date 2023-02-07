package org.haozi.auth.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/6 19:32
 */
public class UsernamePasswordToken extends HaoZiToken {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;

    public UsernamePasswordToken(String username, String password) {
        super("");

    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return userName;
    }
}
