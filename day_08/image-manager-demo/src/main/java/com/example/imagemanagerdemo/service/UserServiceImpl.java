package com.example.imagemanagerdemo.service;

import com.example.imagemanagerdemo.entity.User;
import com.example.imagemanagerdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> get_all_user() {
        return userRepository.findAll();
    }
}
