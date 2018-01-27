package com.victoryw.openid.controller;

import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

import java.io.IOException;

public class DaimlerSSOClient {

    private final AuthorizationCodeResourceDetails oAuthResource;

    public DaimlerSSOClient(AuthorizationCodeResourceDetails authorizationCodeResourceDetails) {
        this.oAuthResource = authorizationCodeResourceDetails;
    }

    String getAuthorizationUrl() {
        return String.format("%s?client_id=%s&response_type=%s&scope=%s&redirect_uri=%s",
                oAuthResource.getUserAuthorizationUri(),
                oAuthResource.getClientId(),
                "code",
                "openid",
                oAuthResource.getPreEstablishedRedirectUri());
    }

    OidcUserDetail getUserDetail(String code) throws IOException {
        TokenProvider tokenProvider = new TokenProvider(code, this.oAuthResource);
        tokenProvider.invoke();
        IdToken idToken = tokenProvider.getIdToken();

        return new OidcUserDetail(idToken.getSub(),
                idToken.getAud(),
                idToken.getExp(),
                tokenProvider.getAuth2Token());
    }


}

