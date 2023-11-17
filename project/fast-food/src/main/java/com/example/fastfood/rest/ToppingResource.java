package com.example.fastfood.rest;

import com.example.fastfood.request.UpsertRequest;
import com.example.fastfood.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/topping")
public class ToppingResource {
    @Autowired
    private ToppingService toppingService;

    //Tao moi
    @PostMapping
    public ResponseEntity<?> createTopping(@RequestBody UpsertRequest request){
        return new ResponseEntity<>(toppingService.createSize(request), HttpStatus.CREATED);
    }
    //Cap nhat
    @PutMapping("{id}")
    public ResponseEntity<?> updateTopping(@PathVariable Long id, @RequestBody UpsertRequest request){
        return ResponseEntity.ok(toppingService.updateTopping(id,request));
    }


    //Xoa
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSize(@PathVariable Long id){
        toppingService.deleteTopping(id);
        return ResponseEntity.noContent().build();
    }
}
