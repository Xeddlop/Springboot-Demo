package com.example.demo.controller;

import com.example.demo.annotation.LoginUser;
import com.example.demo.annotation.Token;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
@Slf4j
public class DashboardController {

    @Autowired
    UserService userService;


    @GetMapping("/dashboard")
    @Token
    public String dashboard(@LoginUser User user, Model model){
        //System.out.println("Dashboard Controller:"+user+" is waiting to be added to model...");
        model.addAttribute("user",user);
        return "dashboard";
    }

    //For test use
    @GetMapping("/noToken")
    public String noToken(){
        System.out.println("Test Success!");
        return "dashboard";
    }
}
