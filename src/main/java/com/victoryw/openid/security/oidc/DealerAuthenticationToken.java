package com.victoryw.openid.security.oidc;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class DealerAuthenticationToken extends AbstractAuthenticationToken {

    private final String sub;

    public DealerAuthenticationToken(String sub) {
        super(null);
        this.sub = sub;
    }

    public DealerAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String sub) {
        super(authorities);
        this.sub = sub;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    public String getSub() {
        return sub;
    }
}
