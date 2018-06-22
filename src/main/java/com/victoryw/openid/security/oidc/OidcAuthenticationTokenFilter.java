package com.victoryw.openid.security.oidc;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OidcAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    protected OidcAuthenticationTokenFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    protected OidcAuthenticationTokenFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        AuthenticationManager authenticationManager = this.getAuthenticationManager();
        Authentication authenticate = authenticationManager.authenticate(new DealerAuthenticationToken("abc"));
        return authenticate;
    }
}


