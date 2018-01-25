package com.victoryw.quartz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final
    OAuth2RestTemplate oAuth2RestTemplate;
    private DaimlerSSOClient daimlerSSOClient;

    @Autowired
    public HomeController(OAuth2RestTemplate oAuth2RestTemplate) {
        this.oAuth2RestTemplate = oAuth2RestTemplate;
        daimlerSSOClient = new DaimlerSSOClient(oAuth2RestTemplate);
    }

    @RequestMapping("/")
    @ResponseBody
    public final String home() {
        return daimlerSSOClient.GetAuthorizationUrl();
    }

}


