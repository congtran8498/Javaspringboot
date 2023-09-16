package com.example.usermanager.controller;

import com.example.usermanager.dto.UserDto;
import com.example.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserViewController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getAllUsers(Model model){
        List<UserDto> userDtoList = userService.get_all_user();
        model.addAttribute("userList", userDtoList);
        return "index";
    }

    @GetMapping("/users/{id}")
    public String getDetailUser(Model model, @PathVariable Integer id){
        UserDto userDto = userService.get_by_id(id);
        model.addAttribute("userDto", userDto);
        return "detail";
    }

    @GetMapping("/users/create")
    public String createUser(Model model){
        return "create";
    }
}
