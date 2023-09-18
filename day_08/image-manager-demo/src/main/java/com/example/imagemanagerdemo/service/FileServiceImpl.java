package com.example.imagemanagerdemo.service;

import com.example.imagemanagerdemo.entity.FileServer;
import com.example.imagemanagerdemo.entity.User;
import com.example.imagemanagerdemo.exception.BadRequestException;
import com.example.imagemanagerdemo.exception.ResouceNotFoundException;
import com.example.imagemanagerdemo.repository.FileServerRepository;
import com.example.imagemanagerdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImpl implements FileService{
    @Autowired
    private FileServerRepository fileServerRepository;
    @Autowired
    private UserRepository userRepository;

    public FileServer uploadFile(Integer userId, MultipartFile file){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResouceNotFoundException("user not found"));
        FileServer fileServer = new FileServer();
        fileServer.setType(file.getContentType());
        try {
            fileServer.setData(file.getBytes());
        } catch (IOException e) {
            throw new BadRequestException("file khong hop le");
        }
        fileServer.setUser(user);
        fileServerRepository.save(fileServer);

        return fileServer;
    }

    public FileServer getFileById(Integer id){
        return fileServerRepository.findById(id)
                .orElseThrow(() -> new ResouceNotFoundException("File not found"));
    }

    public void deleteFile(Integer id){
        FileServer file = getFileById(id);
        fileServerRepository.delete(file);
    }

    @Override
    public List<FileServer> getFileByUserIdOrderByCreatedAt(Integer userId) {
        return fileServerRepository.findByUser_IdOrderByCreatedAtDesc(userId);
    }


}
