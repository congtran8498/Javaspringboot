package com.example.fastfood.controller;

import com.example.fastfood.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("login")
    public String getLogin(){
        return "admin/auth/login";
    }

    @GetMapping("register")
    public String getRegister(){
        return "admin/auth/register";
    }

    @GetMapping("forgot-password")
    public String getForget(){
        return "admin/auth/forgot-password";
    }

    @GetMapping("register/confirm")
    public String getConfirm(@RequestParam String token, Model model){
        model.addAttribute("data", authService.confirm(token));
        return "admin/auth/register-confirm";
    }

    @GetMapping("forgot-password/confirm")
    public String getForgotPasswordConfirm(@RequestParam String token, Model model){
        model.addAttribute("data", authService.confirm(token));
        return "admin/auth/forgot-password-confirm";
    }
}
