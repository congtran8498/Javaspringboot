package com.example.demothymeleaf.controller;

import com.example.demothymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    private List<User> userList;

    public WebController() {
        userList = new ArrayList<>();
        userList.add(new User(1,"Nguyen Van A","a@gmail.com"));
        userList.add(new User(2,"Nguyen Van B","b@gmail.com"));
        userList.add(new User(3,"Nguyen Van C","c@gmail.com"));
    }

    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("user", userList.get(0));
        model.addAttribute("userList", userList);
        return "index";
    }
}
