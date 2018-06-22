package com.victoryw.openid.provider;

import java.io.IOException;

public class SSOClient {

    private final OpenIdResourceDetails openIdResourceDetails;

    public SSOClient(OpenIdResourceDetails openIdResourceDetails) {
        this.openIdResourceDetails = openIdResourceDetails;
    }

    public String getAuthorizationUrl() {
        return String.format("%s?client_id=%s&response_type=%s&scope=%s&redirect_uri=%s",
                openIdResourceDetails.getUserAuthorizationUri(),
                openIdResourceDetails.getClientId(),
                "code",
                "openid",
                openIdResourceDetails.getPreEstablishedRedirectUri());
    }

    public OidcUserDetail getUserDetail(String code) throws IOException {
        TokenProvider tokenProvider = new TokenProvider(code, this.openIdResourceDetails);
        tokenProvider.invoke();
        IdToken idToken = tokenProvider.getIdToken();

        return new OidcUserDetail(idToken.getSub(),
                idToken.getAud(),
                idToken.getExp(),
                tokenProvider.getAuth2Token());
    }


    public String getEndSessionEndPoint() {
        return openIdResourceDetails.getEndSessionEndPoint();
    }
}

