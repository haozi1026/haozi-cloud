package org.haozi.auth.token;

import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/6 20:21
 */
public abstract class HaoZiToken extends AbstractAuthenticationToken {

    public HaoZiToken(String grantType) {
        super(Collections.emptyList());
        this.grantType = grantType;
    }

    /**
     * 认证类型
     */
    private String grantType;

    /**
     * 客户端ID
     */
    private RegisteredClient registeredClient;

    public RegisteredClient getRegisteredClient() {
        return registeredClient;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setRegisteredClient(RegisteredClient clientId) {
        this.registeredClient = clientId;
    }
}
