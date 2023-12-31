package com.example.demosecurity.controller;

import com.example.demosecurity.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class WebController {
    @Autowired
    private IAuthenticationFacade iAuthenticationFacade;
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
    @GetMapping("/book")
    public String getBook(){
        return "admin-page";
    }
    @GetMapping("/user")
    public String getUser(){
        return "admin-page";
    }

    @GetMapping("/get-info")
    public ResponseEntity<?> getInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication.getPrincipal());
    }

    @GetMapping("/get-info-1")
    public ResponseEntity<?> getInfo1(Principal principal){
        return ResponseEntity.ok(principal);
    }
    @GetMapping("/get-info-2")
    public ResponseEntity<?> getInfo2(Authentication authentication){
        return ResponseEntity.ok(authentication);
    }
    @GetMapping("/get-info-3")
    public ResponseEntity<?> getInfo3(){
        Authentication authentication = iAuthenticationFacade.getAuthentication();
        return ResponseEntity.ok(authentication.getName());
    }
}
