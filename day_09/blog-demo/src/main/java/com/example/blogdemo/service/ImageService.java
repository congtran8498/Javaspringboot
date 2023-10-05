package com.example.blogdemo.service;

import com.example.blogdemo.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    List<Image> getFilesOfCurrentUser();
    Image uploadFile(MultipartFile file) throws IOException;
    Image getFileById(Integer id);
    void deleteFile(Integer id);
}
