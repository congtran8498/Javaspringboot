package com.example.blogdemo.controller;


import com.example.blogdemo.entity.Category;
import com.example.blogdemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/categories")
    public String getAllCategory(Model model){
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);
        return "admin/category/index";
    }

    @GetMapping("/admin/categories/create")
    public String createCategory(Model model){
        return "admin/category/create";
    }
}
