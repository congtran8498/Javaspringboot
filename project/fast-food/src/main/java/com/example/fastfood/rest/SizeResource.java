package com.example.fastfood.rest;

import com.example.fastfood.request.UpsertRequest;
import com.example.fastfood.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/size")
public class SizeResource {
    @Autowired
    private SizeService sizeService;
    //Tao moi
    @PostMapping
    public ResponseEntity<?> createSize(@RequestBody UpsertRequest request){
        return new ResponseEntity<>(sizeService.createSize(request), HttpStatus.CREATED);
    }
    //Cap nhat
    @PutMapping("{id}")
    public ResponseEntity<?> updateSize(@PathVariable Long id, @RequestBody UpsertRequest request){
        return ResponseEntity.ok(sizeService.updateSize(id,request));
    }


    //Xoa
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSize(@PathVariable Long id){
        sizeService.deleteSize(id);
        return ResponseEntity.noContent().build();
    }
}
