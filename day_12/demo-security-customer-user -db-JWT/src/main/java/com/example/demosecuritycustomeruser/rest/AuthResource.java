package com.example.demosecuritycustomeruser.rest;

import com.example.demosecuritycustomeruser.request.CreateUserRequest;
import com.example.demosecuritycustomeruser.request.LoginRequest;
import com.example.demosecuritycustomeruser.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthResource {
    @Autowired
    private AuthService authService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody CreateUserRequest request){
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("confirm")
    public ResponseEntity<?> confirm(@RequestParam String token){
        return ResponseEntity.ok(authService.confirm(token));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}

