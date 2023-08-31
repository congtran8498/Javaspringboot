package com.example.usermanager.service;

import com.example.usermanager.dao.UserDAO;
import com.example.usermanager.dto.UserDto;
import com.example.usermanager.exception.BadRequestException;
import com.example.usermanager.exception.ResouceNotFoundException;
import com.example.usermanager.model.User;
import com.example.usermanager.request.CreateUserRequest;
import com.example.usermanager.request.UpdateAvatarRequest;
import com.example.usermanager.request.UpdatePasswordRequest;
import com.example.usermanager.request.UpdateUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = userDAO.findAllUser();
        return userList.stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = userDAO.findUserById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Not found user"));
        return mapToDto(user);
    }

    @Override
    public List<UserDto> getUserByName(String name) {
        List<User> userList = userDAO.findUserByName(name);
        return userList.stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setPassword(request.getPassword());
        user.setAvatar(null);
        return mapToDto(userDAO.saveUser(user));
    }

    @Override
    public void delete(Integer id) {
        User user = userDAO.findUserById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Not found user"));
        userDAO.deleleUser(user);
    }

    @Override
    public UserDto updateUser(Integer id, UpdateUserRequest request) {
        User user = userDAO.findUserById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Not found user"));
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        return mapToDto(user);
    }

    @Override
    public void updateAvatar(Integer id, UpdateAvatarRequest request) {
        User user = userDAO.findUserById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Not found user"));
        user.setAvatar(request.getAvatar());
    }

    @Override
    public void updatePassword(Integer id, UpdatePasswordRequest request) {
        User user = userDAO.findUserById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Not found user"));
        if(!user.getPassword().equals(request.getOldPassWord())){
            throw new BadRequestException("Mật khẩu cũ không chính xác");
        }
        if(user.getPassword().equals(request.getNewPassWord())){
            throw new BadRequestException("Mật khẩu mới không được trùng với mật khẩu cũ");
        }
        user.setPassword(request.getNewPassWord());
    }

    @Override
    public String forgotPassword(Integer id) {
        User user = userDAO.findUserById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Not found user"));
        return userDAO.forgotPassword(user);
    }



    private UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .avatar(user.getAvatar())
                .build();
    }
}
