package com.example.blogdemo.controller;

import com.example.blogdemo.entity.Category;
import com.example.blogdemo.entity.User;
import com.example.blogdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/users")
    public String getAllUser(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/user/index";
    }

    @GetMapping("/admin/users/create")
    public String getCreateUser(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/user/user-create";
    }

    @GetMapping("/admin/users/detail")
    public String getDetailUser(Model model){
        return "admin/user/user-detail";
    }
}

