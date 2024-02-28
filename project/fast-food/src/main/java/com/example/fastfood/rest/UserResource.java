package com.example.fastfood.rest;

import com.example.fastfood.request.ChangePasswordRequest;
import com.example.fastfood.request.UpdateInforUserRequest;
import com.example.fastfood.request.UpdateUserInfor;
import com.example.fastfood.service.ImageService;
import com.example.fastfood.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/user")
public class UserResource {
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;
    // 1.Upload file
    @PostMapping("{id}/images")
    public ResponseEntity<?> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(imageService.uploadFile(id,file));
    }
    // 1.2 .Upload file avatar
    @PostMapping("{id}/avatar/images")
    public ResponseEntity<?> uploadFileAvatar(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(imageService.uploadFileAvatar(id,file));
    }
    // 2. Lấy ảnh của user
    @GetMapping("/{id}/images")
    public ResponseEntity<?> getAllFileByUser(@PathVariable Long id){
        return ResponseEntity.ok(imageService.getFileByUserId(id));
    }
    //3 Change password
    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        return ResponseEntity.ok(userService.changePassword(request));
    }
    //4 update infor
    @PostMapping("/updateInfor")
    public ResponseEntity<?> updateInfor(@RequestBody UpdateInforUserRequest request) {
        return ResponseEntity.ok(userService.updateInfor(request));
    }

    //4 update infor
    @PostMapping("/{id}/update")
    public ResponseEntity<?> updateInforUser(@RequestBody UpdateUserInfor request,@PathVariable Long id) {
        return ResponseEntity.ok(userService.updateUser(request,id));
    }
}
