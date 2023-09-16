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

    //JPA

    void save_all_user();
    List<UserDto> get_all_user();
    List<UserDto> get_by_name(String name);
    UserDto get_by_id(Integer id);
    UserDto create_user(CreateUserRequest request);
    UserDto update_user(Integer id, UpdateUserRequest request);
    void delete_user(Integer id);
    void update_avatar(Integer id, UpdateAvatarRequest request);
    void  update_password(Integer id, UpdatePasswordRequest request);
    String forgot_password(Integer id);

    // local


}
