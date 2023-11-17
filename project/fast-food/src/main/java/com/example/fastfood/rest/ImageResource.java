package com.example.fastfood.rest;

import com.example.fastfood.entity.Image;
import com.example.fastfood.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/admin/image")
public class ImageResource {
    @Autowired
    private ImageService imageService;

    // 2. Xem file
    @GetMapping("/{id}")
    public ResponseEntity<?> readFile(@PathVariable Long id) {
        Image file = imageService.getFileById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getType()))
                .body(file.getData());
    }

    // 3. Xoa file
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Long id) {
        imageService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }

}
