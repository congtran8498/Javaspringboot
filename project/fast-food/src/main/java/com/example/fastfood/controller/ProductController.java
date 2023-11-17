package com.example.fastfood.controller;

import com.example.fastfood.entity.*;
import com.example.fastfood.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private ToppingService toppingService;
    @Autowired
    private ProductSizeService productSizeService;


    // Lấy danh sách product
    @GetMapping("/admin-page/product")
    public String getProductPage(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize,
            Model model){
        Page<Product> pageData = productService.findAll(page,pageSize);
        List<ProductSize> productSizeList = productSizeService.getAll();

        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        model.addAttribute("productSizeList", productSizeList);
        return "admin/product/index";
    }
    // tạo product
    @GetMapping("/admin-page/product/create")
    public String createProductPage(Model model){
        List<Category> categoryList = categoryService.getAllCategory();
        List<Size> sizeList = sizeService.getAllSize();
        List<Topping> toppingList = toppingService.getAllTopping();
        model.addAttribute("categories", categoryList);
        model.addAttribute("sizeList", sizeList);
        model.addAttribute("toppingList", toppingList);
        return "admin/product/create";
    }

    // chi tiết product
    @GetMapping("/admin-page/product/{id}")
    public String detailProduct(Model model,@PathVariable Long id){
        Product product = productService.findById(id);
        List<Category> categoryList = categoryService.getAllCategory();
        List<Size> sizeList = sizeService.getAllSize();
        model.addAttribute("product",product);
        model.addAttribute("categories", categoryList);
        model.addAttribute("sizeList", sizeList);
        return "admin/product/detail";
    }
}
