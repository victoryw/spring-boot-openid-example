package com.victoryw.openid.DaimlerOpenIdProvider;

import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

public class OpenIdResourceDetails extends AuthorizationCodeResourceDetails {

    private String endSessionEndPoint;

    public void setEndSessionEndPoint(String endSessionEndPoint) {
        this.endSessionEndPoint = endSessionEndPoint;
    }

    public String getEndSessionEndPoint() {
        return endSessionEndPoint;
    }
}
