package com.laberit.sina.bootcamp.modulo3.spring_web.controller.frontoffice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
public class UserFrontController {

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
