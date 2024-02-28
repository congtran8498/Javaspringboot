package com.example.fastfood.controller;

import com.example.fastfood.entity.*;
import com.example.fastfood.service.RoleService;
import com.example.fastfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    // Lấy danh sách user
    @GetMapping("/admin-page/user")
    public String getProductPage(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize,
            Model model){
        Page<User> pageData = userService.getAllUser(page,pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "admin/user/index";
    }
    // chi tiết product
    @GetMapping("/admin-page/user/{id}")
    public String detailUser(Model model,@PathVariable Long id){
        User user = userService.findById(id);
        List<Role> roleList = roleService.getAllRole();
        List<String> statusList = Arrays.stream(User.Status.values())
                .map(User.Status::getValue)
                .collect(Collectors.toList());

        model.addAttribute("user",user);
        model.addAttribute("roleList",roleList);
        model.addAttribute("statusList",statusList);
        return "admin/user/detail";
    }
}
