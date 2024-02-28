package com.example.fastfood.controller;

import com.example.fastfood.entity.*;
import com.example.fastfood.security.CustomUserDetails;
import com.example.fastfood.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ContactService contactService;

    @GetMapping("/")
    public String getHome(Model model,
                          @RequestParam(required = false, defaultValue = "1") Integer page,
                          @RequestParam(required = false, defaultValue = "5") Integer pageSize){
        Page<Product> productList = productService.findAll(page,pageSize);
        List<Category> categoryList = categoryService.getAllCategory();
        List<Contact> contactList = contactService.findContactByStatusValue();
        model.addAttribute("productList",productList);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("contactList",contactList);
        return "web/index";
    }

    @GetMapping("/menu")
    public String getMenu(Model model,
                          @RequestParam(required = false, defaultValue = "1") Integer page,
                          @RequestParam(required = false, defaultValue = "5") Integer pageSize){
        Page<Product> productList = productService.findAll(page,pageSize);
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("productList",productList);
        model.addAttribute("categoryList",categoryList);
        return "web/menu";
    }
    @GetMapping("/cart")
    public String getCart(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ( authentication instanceof AnonymousAuthenticationToken) {
            System.out.println("12");
            // Người dùng chưa đăng nhập
            return "redirect:/?openLogin=true";
        } else {
            // Người dùng đã đăng nhập
            System.out.println("34");
            ShoppingCart shoppingCart = shoppingCartService.getCart();
            model.addAttribute("cart",shoppingCart);
            return "web/cart";
        }


    }
    @GetMapping("/cart-finish")
    public String getCartFinish(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ( authentication instanceof AnonymousAuthenticationToken) {
            // Người dùng chưa đăng nhập
            return "redirect:/?openLogin=true";
        } else {
            // Người dùng đã đăng nhập
            User user = userService.getUser();
            ShoppingCart shoppingCart = user.getShoppingCart();
            model.addAttribute("cart",shoppingCart);
            model.addAttribute("user",user);
            return "web/cart-finish";
        }

    }

    @GetMapping("/change-password")
    public String getChangePassword(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ( authentication instanceof AnonymousAuthenticationToken) {
            // Người dùng chưa đăng nhập
            return "redirect:/?openLogin=true";
        } else {
            // Người dùng đã đăng nhập
            return "web/change-password";
        }

    }

    @GetMapping("/myprofile")
    public String getUserProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ( authentication instanceof AnonymousAuthenticationToken) {
            // Người dùng chưa đăng nhập
            return "redirect:/?openLogin=true";
        } else {
            // Người dùng đã đăng nhập
            User user = userService.getUser();
            model.addAttribute("user",user);
            return "web/profile-user";
        }
    }

    @GetMapping("/contact")
    public String getContact(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ( authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/?openLogin=true";
        } else {
            ShoppingCart shoppingCart = shoppingCartService.getCart();
            model.addAttribute("cart",shoppingCart);
            return "web/contact-us";
        }


    }


}
