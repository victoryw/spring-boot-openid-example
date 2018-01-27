package com.victoryw.openid.controller;

import com.victoryw.openid.DaimlerOpenIdProvider.DaimlerSSOClient;
import com.victoryw.openid.DaimlerOpenIdProvider.OpenIdResourceDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Arrays;

@Configuration
@EnableOAuth2Client
public class DaimlerOpenIdConnectConfig {
    @Value("${google.clientId}")
    private String clientId;

    @Value("${google.clientSecret}")
    private String clientSecret;

    @Value("${google.accessTokenUri}")
    private String accessTokenUri;

    @Value("${google.userAuthorizationUri}")
    private String userAuthorizationUri;

    @Value("${google.redirectUri}")
    private String redirectUri;

    @Value("${google.end_session_endpoint}")
    private String endSessionEndPoint;

    @Bean
    public OpenIdResourceDetails googleOpenId() {
        OpenIdResourceDetails details = new OpenIdResourceDetails();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(accessTokenUri);
        details.setUserAuthorizationUri(userAuthorizationUri);
        details.setScope(Arrays.asList("openid"));
        details.setPreEstablishedRedirectUri(redirectUri);
        details.setUseCurrentUri(false);
        details.setEndSessionEndPoint(endSessionEndPoint);

        return details;
    }

    @Bean
    public DaimlerSSOClient daimlerSSOClient(){
        return new DaimlerSSOClient(googleOpenId());
    }
}
