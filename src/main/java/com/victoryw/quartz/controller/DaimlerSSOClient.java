package com.victoryw.quartz.controller;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

public class DaimlerSSOClient {

    private final OAuth2RestTemplate oAuth2RestTemplate;
    private final AuthorizationCodeResourceDetails oAuthResource;

    public DaimlerSSOClient(OAuth2RestTemplate oAuth2RestTemplate) {

        this.oAuth2RestTemplate = oAuth2RestTemplate;
        this.oAuthResource = (AuthorizationCodeResourceDetails)oAuth2RestTemplate.getResource();
    }

    String GetAuthorizationUrl() {

        //http://localhost:3001/auth?client_id=foo&response_type=code&scope=openid
        AccessTokenRequest request = new DefaultAccessTokenRequest();
        String redirectUri = String.format("%s?client_id=%s&response_type=%s&scope=%s&redirect_uri=%s",
                oAuthResource.getUserAuthorizationUri(),
                oAuthResource.getClientId(),
                "code",
                "openid",
                oAuthResource.getPreEstablishedRedirectUri());
        return redirectUri;
    }

}
