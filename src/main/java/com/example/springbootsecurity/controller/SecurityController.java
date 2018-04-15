package com.example.springbootsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {


    @GetMapping("")
    public String index() {

        return "index";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }


}

































