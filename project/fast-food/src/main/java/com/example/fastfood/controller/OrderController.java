package com.example.fastfood.controller;

import com.example.fastfood.entity.Order;
import com.example.fastfood.entity.ShoppingCart;
import com.example.fastfood.service.OrderService;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/admin-page/order")
    public String getOrderPage(
            Model model,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize){
        Page<Order> pageData = orderService.findAll(page,pageSize);
        List<String> statusList = Arrays.stream(Order.Status.values())
                .map(Order.Status::getValue)
                .collect(Collectors.toList());
        model.addAttribute("pageData",pageData);
        model.addAttribute("currentPage", page);
        model.addAttribute("statusList",statusList);
        return "admin/order/index";
    }

    @GetMapping("/order")
    public String getOrderUser(
            Model model,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ( authentication instanceof AnonymousAuthenticationToken) {

            // Người dùng chưa đăng nhập
            return "redirect:/?openLogin=true";
        } else {
            // Người dùng đã đăng nhập
            Page<Order> pageData = orderService.findAllByUser(page,pageSize);
            model.addAttribute("pageData",pageData);
            model.addAttribute("currentPage", page);
            return "web/order";
        }
    }
    @GetMapping("/product-detail/{id}")
    public String getProductDetail(@PathVariable Long id, Model model){
        Order order = orderService.findOrderById(id);
        model.addAttribute("order",order);
        return "web/productDetail";
    }
}
