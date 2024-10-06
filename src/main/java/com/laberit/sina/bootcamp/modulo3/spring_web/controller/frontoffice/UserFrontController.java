package com.laberit.sina.bootcamp.modulo3.spring_web.controller.frontoffice;

import com.laberit.sina.bootcamp.modulo3.spring_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UserFrontController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(String name, String email, String password) {
        if (userService.registerUser(name, email, password)) {
            return "redirect:/login";
        } else {
            return "redirect:/register?error";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(String email, String password) {
        userService.loginUser(email, password);
        return "redirect:/products";
    }
}
