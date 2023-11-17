package com.example.fastfood.controller;

import com.example.fastfood.entity.Category;
import com.example.fastfood.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin-page/categories")
    public String getCategories(Model model){
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categoryList",categoryList);
        return "admin/category/index";
    }

}
