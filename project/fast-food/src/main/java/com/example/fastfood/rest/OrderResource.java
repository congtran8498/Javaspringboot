package com.example.fastfood.rest;

import com.example.fastfood.request.OrderDetailRequest;
import com.example.fastfood.request.OrderStatusRequest;
import com.example.fastfood.request.UserInforRequest;
import com.example.fastfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderResource {
    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<?> saveOrder(@RequestBody UserInforRequest request){
        return ResponseEntity.ok(orderService.save(request));
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody OrderStatusRequest request){
        return ResponseEntity.ok(orderService.updateStatus(request));
    }
    @PostMapping("/updateStatusByUser")
    public ResponseEntity<?> updateStatusByUser(@RequestBody OrderStatusRequest request){
        return ResponseEntity.ok(orderService.updateStatusByUser(request));
    }

}
