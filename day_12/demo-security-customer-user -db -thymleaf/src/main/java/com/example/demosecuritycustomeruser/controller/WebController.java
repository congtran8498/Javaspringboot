package com.example.demosecuritycustomeruser.controller;

import com.example.demosecuritycustomeruser.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    @Autowired
    private AuthService authService;
    @GetMapping("/")
    public String getHome(){
        return "index";
    }

    @GetMapping("/profile")
    public String getProfile(){
        return "profile";
    }

    @GetMapping("/admin")
    public String getAdmin(){
        return "admin";
    }

    @GetMapping("login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("register")
    public String getRegister(){
        return "register";
    }
    @GetMapping("confirm")
    public String getConfirm(@RequestParam String token, Model model){
        model.addAttribute("data", authService.confirm(token));
        return "confirm";
    }
    @GetMapping("forgot-password")
    public String getForgotpassword(){
        return "forgot-password";
    }

    @GetMapping("forgot-password/confirm")
    public String getForgotPasswordConfirm(@RequestParam String token, Model model){
        model.addAttribute("data", authService.confirm(token));
        return "confirm-forgot-password";
    }
}
