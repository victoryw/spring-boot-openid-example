package com.victoryw.openid.security.oidc;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        DealerAuthenticationToken inputAuth = (DealerAuthenticationToken)authentication;
        return new DealerAuthenticationToken(new ArrayList<>(),inputAuth.getSub());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(DealerAuthenticationToken.class);
    }
}
