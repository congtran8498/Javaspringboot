package com.example.fastfood.rest;

import com.example.fastfood.request.dto.CreatePizzaRequestDto;
import com.example.fastfood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/product")
public class ProductResource {
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<?> getAllBlog(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize){
        return ResponseEntity.ok(productService.findAll(page,pageSize));
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody CreatePizzaRequestDto request){
        return new ResponseEntity<>(productService.createProduct(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBlog(@PathVariable Long id, @RequestBody CreatePizzaRequestDto request){
        return ResponseEntity.ok(productService.updateProduct(id,request));
    }
}
