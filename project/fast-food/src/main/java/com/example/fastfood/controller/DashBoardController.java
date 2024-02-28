package com.example.fastfood.controller;

import com.example.fastfood.entity.Order;
import com.example.fastfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class DashBoardController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/admin-page/dashboard")
    public String getDashboard(Model model){
        List<Order> orderList = orderService.findOrderByStatusValue();
        model.addAttribute("orderList",orderList);
        return "admin/dashboard/index";
    }
}
