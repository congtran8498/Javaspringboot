package com.example.blogdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String getLogin(){
        return "admin/auth/login";
    }
    @GetMapping("/register")
    public String getRegister(){
        return "admin/auth/register";
    }
}

