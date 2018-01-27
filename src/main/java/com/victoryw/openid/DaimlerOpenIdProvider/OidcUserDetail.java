package com.victoryw.openid.DaimlerOpenIdProvider;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

@Getter
@Setter
public class OidcUserDetail {

    private String sub;
    private String aud;
    private Long exp;

    @Getter()
    private OAuth2AccessToken accessToken;

    public OidcUserDetail() {
    }

    public OidcUserDetail(String sub, String aud, Long exp, OAuth2AccessToken accessToken) {

        this.sub = sub;
        this.aud = aud;
        this.exp = exp;
        this.accessToken = accessToken;
    }
}
