package com.example.fastfood.rest;

import com.example.fastfood.repository.UserRepository;
import com.example.fastfood.request.ItemCartRequest;
import com.example.fastfood.request.OrderDetailRequest;
import com.example.fastfood.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class ShoppingCartResource {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody OrderDetailRequest request){
        return ResponseEntity.ok(shoppingCartService.addToCart(request));
    }
    @PostMapping("/updateCart")
    public ResponseEntity<?> updateCart(@RequestBody OrderDetailRequest request){
        return ResponseEntity.ok(shoppingCartService.updateCart(request));
    }
    @PostMapping("/updateItem")
    public ResponseEntity<?> updateItem(@RequestBody ItemCartRequest request){
        return ResponseEntity.ok(shoppingCartService.updateItem(request));
    }
    @GetMapping
    public ResponseEntity<?> getCart(){
        return ResponseEntity.ok(shoppingCartService.getCart());
    }

    @PostMapping ("/deleteItem")
    public ResponseEntity<?> deleteItem(@RequestBody OrderDetailRequest request){
        return ResponseEntity.ok(shoppingCartService.deleteItemFromCart(request));
    }
    @PostMapping ("/deleteItem/{id}")
    public ResponseEntity<?> deleteItem2(@PathVariable Long id){
        return ResponseEntity.ok(shoppingCartService.deleteItem(id));
    }
}
