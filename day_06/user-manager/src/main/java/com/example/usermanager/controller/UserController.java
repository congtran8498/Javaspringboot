package com.example.usermanager.controller;

import com.example.usermanager.dto.UserDto;
import com.example.usermanager.request.CreateUserRequest;
import com.example.usermanager.request.UpdateAvatarRequest;
import com.example.usermanager.request.UpdatePasswordRequest;
import com.example.usermanager.request.UpdateUserRequest;
import com.example.usermanager.response.UserResponse;
import com.example.usermanager.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //1. Lấy danh sách theo tên
    @GetMapping("users")
    public ResponseEntity<?> getAllUser(){
        List<UserDto> userDtoList = userService.getAllUser();
        UserResponse userResponse = new UserResponse(userDtoList,1,10,5);
        return ResponseEntity.ok(userResponse);
    }

    //2. Tìm kiếm user theo tên
    @GetMapping("search")
    public ResponseEntity<?> getUserByName(@RequestParam String name){
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    //3. Lấy chi tiết user theo id
    @GetMapping("users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    //4. Tạo mới user
    @PostMapping("users")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request){
        return ResponseEntity.ok(userService.createUser(request));
    }

    //5. Cập nhật thông tin user
    @PutMapping("users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UpdateUserRequest request){
        return ResponseEntity.ok(userService.updateUser(id,request));
    }
    //6. Xóa user
    @DeleteMapping("users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //7. Thay đổi ảnh avatar
    @PutMapping("users/{id}/update-avatar")
    public ResponseEntity<?> updateAvatar(@PathVariable Integer id, @RequestBody UpdateAvatarRequest request){
        userService.updateAvatar(id,request);
        return ResponseEntity.noContent().build();
    }

    //8. Thay đổi mật khẩu
    @PutMapping("users/{id}/update-password")
    public ResponseEntity<?> updatePassword(@PathVariable Integer id, @RequestBody UpdatePasswordRequest request){
        userService.updatePassword(id,request);
        return ResponseEntity.noContent().build();
    }

    //9. Quên mật khẩu
    @PutMapping("users/{id}/forgot-password")
    public ResponseEntity<?> forgotPassWord(@PathVariable Integer id){
        return ResponseEntity.ok(userService.forgotPassword(id));
    }

}
