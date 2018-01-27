package com.victoryw.openid.controller;

import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

public class OAuth2RestTemplateFactory {
    static OAuth2RestTemplate createOAuth2Template(String code, AuthorizationCodeResourceDetails oAuthResource) {
        DefaultAccessTokenRequest request = new DefaultAccessTokenRequest();
        request.setAuthorizationCode(code);
        DefaultOAuth2ClientContext context = new DefaultOAuth2ClientContext(request);

        OAuth2RestTemplate auth2RestTemplate = new OAuth2RestTemplate(oAuthResource, context);

        AuthorizationCodeAccessTokenProvider authCodeProvider = new AuthorizationCodeAccessTokenProvider();
        authCodeProvider.setStateMandatory(false);
        auth2RestTemplate.setAccessTokenProvider(authCodeProvider);

        return auth2RestTemplate;
    }
}
