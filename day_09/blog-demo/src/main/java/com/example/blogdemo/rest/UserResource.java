package com.example.blogdemo.rest;

import com.example.blogdemo.request.CreateUserRequest;
import com.example.blogdemo.request.UpdateUserRequest;
import com.example.blogdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserResource {
    @Autowired
    private UserService userService;

    //1. Lấy danh sách user
    @GetMapping
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    //2. Tạo user
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request){
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    //3. xem chi tiet user
    @GetMapping("{id}")
    public ResponseEntity<?> getUserId(@PathVariable Integer id){
        return ResponseEntity.ok(userService.findById(id));
    }
    //4 sua user
    @PutMapping("{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Integer id, @RequestBody UpdateUserRequest request){
        return ResponseEntity.ok(userService.updateUser(id,request));
    }


    //5. reset password
    @PostMapping("{id}/reset-password")
    public ResponseEntity<?> resetPassword(@PathVariable Integer id){
        return null;
    }


}
