package com.victoryw.openid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CodeRestrictedController {

    @RequestMapping("/oidc-restricted/example")
    @ResponseBody
    public final String home() {

        String s = "you are successful to access restricted resource";
        return s;
    }
}
