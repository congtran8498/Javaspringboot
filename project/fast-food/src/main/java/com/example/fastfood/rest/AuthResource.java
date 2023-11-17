package com.example.fastfood.rest;

import com.example.fastfood.request.CreateUserRequest;
import com.example.fastfood.request.ForgotPasswordRequest;
import com.example.fastfood.request.LoginUserRequest;
import com.example.fastfood.request.RecoverPasswordRequest;
import com.example.fastfood.service.AuthService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthResource {
    @Autowired
    private AuthService authService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody CreateUserRequest request) throws MessagingException {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }


    @PostMapping("forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) throws MessagingException {
        return new ResponseEntity<>(authService.forgotPassWordToken(request), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("recover-password")
    public ResponseEntity<?> updatePassword(@RequestBody RecoverPasswordRequest request){
        return ResponseEntity.ok(authService.updatePassword(request));
    }
}
