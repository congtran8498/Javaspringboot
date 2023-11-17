package com.example.fastfood.controller;

import com.example.fastfood.entity.Product;
import com.example.fastfood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String getHome(Model model){
        List<Product> productList = productService.getAllProduct();
        model.addAttribute("productList",productList);
        return "web/index";
    }
}
