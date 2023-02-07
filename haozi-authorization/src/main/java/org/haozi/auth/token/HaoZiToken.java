package org.haozi.auth.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/6 20:21
 */
public abstract class HaoZiToken extends AbstractAuthenticationToken {

    private String grantType;

    public HaoZiToken(String grantType) {
        super(Collections.emptyList());
        this.grantType = grantType;
    }


}
