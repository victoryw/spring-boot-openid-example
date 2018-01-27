package com.victoryw.openid.controller;

import com.victoryw.openid.DaimlerOpenIdProvider.DaimlerSSOClient;
import com.victoryw.openid.DaimlerOpenIdProvider.OidcUserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ShowInfoController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final DaimlerSSOClient daimlerSSOClient;

    @Autowired
    public ShowInfoController(DaimlerSSOClient daimlerSSOClient) {
        this.daimlerSSOClient = daimlerSSOClient;
    }

    @RequestMapping("/success")
    @ResponseBody
    public final String home() {

        return "success";
    }
}


