package vn.techmaster.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.demo.modal.Color;
import vn.techmaster.demo.modal.User;
import vn.techmaster.demo.service.UserService;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/getAll")
    public List<User> allUser(){
        return userService.all();
    }
    @GetMapping("/login")
    public ResponseEntity<?> getUser(@RequestParam String userName, @RequestParam String passWord){
        return ResponseEntity.ok(userService.findUser(userName,passWord));
    }
}
