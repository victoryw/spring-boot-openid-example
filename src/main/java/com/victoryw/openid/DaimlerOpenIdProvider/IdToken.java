package com.victoryw.openid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.io.IOException;
import java.util.Map;

public class IdToken {

    private final Map idTokenInfo;

    private IdToken(OAuth2AccessToken auth2Token) throws IOException {
        String idTokenValue = auth2Token.getAdditionalInformation().get("id_token").toString();
        Jwt tokenDecoded = JwtHelper.decode(idTokenValue);
        this.idTokenInfo = new ObjectMapper().readValue(tokenDecoded.getClaims(), Map.class);
    }

    Long getExp() {
        return Long.valueOf(idTokenInfo.get("exp").toString());
    }

    String getAud() {
        return idTokenInfo.get("aud").toString();
    }

    String getSub() {
        return idTokenInfo.get("sub").toString();
    }
}
