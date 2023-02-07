package org.haozi.auth.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/6 20:21
 */
public abstract class HaoZiToken extends AbstractAuthenticationToken {
    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public HaoZiToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }
}
