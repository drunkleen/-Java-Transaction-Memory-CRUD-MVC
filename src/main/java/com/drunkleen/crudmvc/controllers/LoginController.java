package com.drunkleen.crudmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }

    // @GetMapping("/logout")
    // public String logout() {
    //     return "/auth/logout";
    // }
}

