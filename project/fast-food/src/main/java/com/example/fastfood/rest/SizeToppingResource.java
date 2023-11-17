package com.example.fastfood.rest;

import com.example.fastfood.request.dto.ToppingSizeDto;
import com.example.fastfood.service.SizeToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/admin/size-topping")
public class SizeToppingResource {
    @Autowired
    private SizeToppingService sizeToppingService;

    //Tao moi
    @PostMapping
    public ResponseEntity<?> createSizeTopping(@RequestBody ToppingSizeDto request){
        return new ResponseEntity<>(sizeToppingService.createSizeTopping(request), HttpStatus.CREATED);
    }
    //Cap nhat
    @PutMapping("{id}")
    public ResponseEntity<?> updateSizeTopping(@PathVariable Long id, @RequestBody ToppingSizeDto request){
        return ResponseEntity.ok(sizeToppingService.updateSizeTopping(id,request));
    }

    //Xoa
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSizeTopping(@PathVariable Long id){
        sizeToppingService.deleteSizeTopping(id);
        return ResponseEntity.noContent().build();
    }
}
