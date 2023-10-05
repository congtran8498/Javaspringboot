package com.example.imagemanagerdemo.controller;

import com.example.imagemanagerdemo.entity.FileServer;
import com.example.imagemanagerdemo.entity.User;
import com.example.imagemanagerdemo.service.FileService;
import com.example.imagemanagerdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;
    // API tra ve HTML
    @GetMapping("/users")
    public String getUserPage(Model model) {
        List<User> userList = userService.get_all_user();
        model.addAttribute("userList",userList);
        return "index";
    }

    @GetMapping("/users/{id}/files")
    public String getFilesPage(Model model, @PathVariable Integer id) {
        List<FileServer> fileServers = fileService.getFileByUserIdOrderByCreatedAt(id);
        model.addAttribute("files", fileServers);
        model.addAttribute("userId", id);
        return "file";
    }

    //API tra ve json
    @PostMapping("/api/users/{id}/files")
    public ResponseEntity<?> uploadFile(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(fileService.uploadFile(id,file));
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> getAllFileByUser(@PathVariable Integer id){
        return ResponseEntity.ok(fileService.getFileByUserIdOrderByCreatedAt(id));
    }
}
