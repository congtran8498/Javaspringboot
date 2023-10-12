package com.example.blogdemo.service;

import com.example.blogdemo.entity.User;
import com.example.blogdemo.model.projection.UserInfo;
import com.example.blogdemo.request.CreateUserRequest;
import com.example.blogdemo.request.UpdateUserRequest;

import java.util.List;

public interface UserService {
    List<UserInfo> getAllUser();
    List<User> getAllUser1();

    UserInfo createUser(CreateUserRequest request);
    UserInfo updateUser(Integer id, UpdateUserRequest request);

    UserInfo findById(Integer id);
}
