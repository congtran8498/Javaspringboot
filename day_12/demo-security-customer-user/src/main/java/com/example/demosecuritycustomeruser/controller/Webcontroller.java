package com.example.demosecuritycustomeruser.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Webcontroller {
    @GetMapping("/")
    public String getHome(){
        return "home-page";
    }

    @GetMapping("/profile")
    public String getProfile(){
        return "profile-page";
    }

    @GetMapping("/admin")
    public String getAdmin(){
        return "admin-page";
    }
}
