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
public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final DaimlerSSOClient daimlerSSOClient;

    @Autowired
    public HomeController(DaimlerSSOClient daimlerSSOClient) {
        this.daimlerSSOClient = daimlerSSOClient;
    }

    @RequestMapping("/authorization-endpoint")
    @ResponseBody
    public final String home() {
        return daimlerSSOClient.getAuthorizationUrl();
    }

    @RequestMapping(value = "/google-login", method = RequestMethod.GET)
    @ResponseBody
    public final void getIdToken(HttpServletResponse httpServletResponse, @RequestParam("code") String code) throws IOException {
        OidcUserDetail userDetail = daimlerSSOClient.getUserDetail(code);
        Cookie cookie = new Cookie("id", userDetail.getSub());
        cookie.setMaxAge(-1);
        httpServletResponse.addCookie(cookie);
        httpServletResponse.sendRedirect("/success");

    }

    @RequestMapping("/end-session-end-point")
    @ResponseBody
    public final String logout(){
        return daimlerSSOClient.getEndSessionEndPoint();
    }

}


