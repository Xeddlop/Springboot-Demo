package com.example.demo.controller;

import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


//This controller returns json data
//Used to capture http post request and respond
@RestController
public class InterfaceController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/register")
    public Result registerResult(User user) {
        System.out.println(user);
        return (userService.register(user));
    }

    @PostMapping(value = "/login")
    public Result loginResult(User user) {
        System.out.println(user+" is trying to login...");
        return (userService.login(user));
    }
}
