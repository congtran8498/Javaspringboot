package com.example.exsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String getHome(){
        return "index";
    }

    @GetMapping("/blog")
    public String getBlog(){
        return "blog";
    }

    @GetMapping("/admin/brand")
    public String getBrand(){
        return "brand";
    }

    @GetMapping("/admin/category")
    public String getCategory(){
        return "category";
    }

    @GetMapping("/admin/order")
    public String getOrder(){
        return "order";
    }

    @GetMapping("/admin/product")
    public String getProduct(){
        return "product";
    }

    @GetMapping("/admin/dashboard")
    public String getDashboard(){
        return "dashboard";
    }

    @GetMapping("/user-management")
    public String getUser(){
        return "user";
    }

    @GetMapping("/profile")
    public String getProfile(){
        return "profile";
    }
}
