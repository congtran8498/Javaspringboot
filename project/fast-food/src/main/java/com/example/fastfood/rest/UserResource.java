package com.example.fastfood.rest;

import com.example.fastfood.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/user")
public class UserResource {
    @Autowired
    private ImageService imageService;
    // 1.Upload file
    @PostMapping("{id}/images")
    public ResponseEntity<?> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(imageService.uploadFile(id,file));
    }
    // 2. Lấy ảnh của user
    @GetMapping("/{id}/images")
    public ResponseEntity<?> getAllFileByUser(@PathVariable Long id){
        return ResponseEntity.ok(imageService.getFileByUserId(id));
    }
}
