package com.example.demothymleafspringsecurity.controller;

import com.example.demothymleafspringsecurity.security.IsAdmin;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String getHome(){
        return "index";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/profile")
    public String getProfile(){
        return "profile";
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @IsAdmin
    @GetMapping("/admin")
    public String getAdmin(){
        return "admin";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
}
