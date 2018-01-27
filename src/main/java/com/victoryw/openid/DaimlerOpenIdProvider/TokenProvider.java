package com.victoryw.openid.controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import java.io.IOException;

public class TokenProvider {
    private OAuth2RestTemplate auth2RestTemplate;
    private OAuth2AccessToken auth2Token;
    private IdToken idToken;

    public TokenProvider(String code, AuthorizationCodeResourceDetails oAuthResource) {
        this.auth2RestTemplate = OAuth2RestTemplateFactory.createOAuth2Template(
                code,
                oAuthResource);
    }

    public void invoke() throws IOException {
        try {
            auth2Token = auth2RestTemplate.getAccessToken();
        } catch (final OAuth2Exception e) {
            throw new BadCredentialsException("Could not obtain access token", e);
        }

        this.idToken = new IdToken(auth2Token);
    }

    public IdToken getIdToken() {
        return idToken;
    }

    public OAuth2AccessToken getAuth2Token() {
        return auth2Token;
    }

}

