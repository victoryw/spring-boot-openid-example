package com.victoryw.quartz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private DaimlerSSOClient daimlerSSOClient;

    @Autowired
    public HomeController(OAuth2RestTemplate oAuth2RestTemplate) {
        daimlerSSOClient = new DaimlerSSOClient(oAuth2RestTemplate);
    }

    @RequestMapping("/")
    @ResponseBody
    public final String home() {
        return daimlerSSOClient.getAuthorizationUrl();
    }

    @RequestMapping(value = "/google-login", method = RequestMethod.GET)
    @ResponseBody
    public final OidcUserDetail getIdToken(@RequestParam("code") String code) throws IOException {
        return daimlerSSOClient.getUserDetail(code);

    }

}


