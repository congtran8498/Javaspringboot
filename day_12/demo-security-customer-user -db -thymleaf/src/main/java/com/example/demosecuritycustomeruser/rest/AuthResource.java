package com.example.demosecuritycustomeruser.rest;

import com.example.demosecuritycustomeruser.request.ChangePassword;
import com.example.demosecuritycustomeruser.request.CreateUserRequest;
import com.example.demosecuritycustomeruser.request.ForgotPasswordRequest;
import com.example.demosecuritycustomeruser.request.LoginRequest;
import com.example.demosecuritycustomeruser.service.AuthService;
import jakarta.mail.MessagingException;
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
    public ResponseEntity<?> register(@RequestBody CreateUserRequest request) throws MessagingException {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

//    @PostMapping("confirm")
//    public ResponseEntity<?> confirm(@RequestParam String token){
//        return ResponseEntity.ok(authService.confirm(token));
//    }

    @PostMapping("forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) throws MessagingException {
        return new ResponseEntity<>(authService.forgotPassWordToken(request), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("update-password")
    public ResponseEntity<?> updatePassword(@RequestBody ChangePassword request){
        return ResponseEntity.ok(authService.updatePassword(request));
    }
}

