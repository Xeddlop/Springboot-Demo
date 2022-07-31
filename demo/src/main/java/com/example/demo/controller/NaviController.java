package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
//This controller will navigate user to correct page.
//Only return html page
public class NaviController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(){
        return("login.html");
    }

    @GetMapping("/register")
    public String register(){
        return("register.html");
    }
}

