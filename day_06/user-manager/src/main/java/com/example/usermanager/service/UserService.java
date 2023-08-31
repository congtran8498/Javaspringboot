package com.example.usermanager.service;

import com.example.usermanager.dto.UserDto;
import com.example.usermanager.model.User;
import com.example.usermanager.request.CreateUserRequest;
import com.example.usermanager.request.UpdateAvatarRequest;
import com.example.usermanager.request.UpdatePasswordRequest;
import com.example.usermanager.request.UpdateUserRequest;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUser();
    UserDto getUserById(Integer id);
    List<UserDto> getUserByName(String name);
    UserDto createUser(CreateUserRequest request);
    void delete(Integer id);
    UserDto updateUser(Integer id, UpdateUserRequest request);
    void updateAvatar(Integer id, UpdateAvatarRequest request);
    void updatePassword(Integer id, UpdatePasswordRequest request);
    String forgotPassword(Integer id);
}
