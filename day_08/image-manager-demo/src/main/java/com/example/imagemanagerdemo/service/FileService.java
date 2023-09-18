package com.example.imagemanagerdemo.service;

import com.example.imagemanagerdemo.entity.FileServer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileServer uploadFile(Integer userId, MultipartFile file);
    FileServer getFileById(Integer id);
    void deleteFile(Integer id);

    List<FileServer> getFileByUserIdOrderByCreatedAt(Integer userId);
}
