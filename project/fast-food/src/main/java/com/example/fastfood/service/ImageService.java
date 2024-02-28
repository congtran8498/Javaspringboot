package com.example.fastfood.service;

import com.example.fastfood.entity.Image;
import com.example.fastfood.entity.Product;
import com.example.fastfood.entity.User;
import com.example.fastfood.exception.BadRequestException;
import com.example.fastfood.exception.NotFoundException;
import com.example.fastfood.repository.ImageRepository;
import com.example.fastfood.repository.ProductRepository;
import com.example.fastfood.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;

    public Image uploadFile(Long userId, MultipartFile file){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("user not found"));
        Image fileServer = new Image();
        fileServer.setType(file.getContentType());
        fileServer.setImageFor("product");
        try {
            fileServer.setData(file.getBytes());
        } catch (IOException e) {
            throw new BadRequestException("image không hợp lệ");
        }
        fileServer.setUser(user);
        imageRepository.save(fileServer);

        return fileServer;
    }
    public Image uploadFileAvatar(Long userId, MultipartFile file){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("user not found"));
        Image fileServer = new Image();
        fileServer.setType(file.getContentType());
        fileServer.setImageFor("avatar");
        try {
            fileServer.setData(file.getBytes());
        } catch (IOException e) {
            throw new BadRequestException("image không hợp lệ");
        }
        fileServer.setUser(user);
        imageRepository.save(fileServer);
        return fileServer;
    }

    public Image getFileById(Long id){
        return imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Image not found"));
    }

    public void deleteFile(Long id){
        Image file = getFileById(id);
        imageRepository.delete(file);
    }

    public List<Image> getFileByUserId(Long userId) {
        return imageRepository.findByUser_IdOrderByCreatedAtDesc(userId);
    }
}
