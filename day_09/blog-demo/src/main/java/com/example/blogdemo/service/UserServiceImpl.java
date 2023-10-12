package com.example.blogdemo.service;

import com.example.blogdemo.entity.Role;
import com.example.blogdemo.entity.User;
import com.example.blogdemo.exception.BadRequestException;
import com.example.blogdemo.exception.NotFoundException;
import com.example.blogdemo.model.projection.UserInfo;
import com.example.blogdemo.repository.RoleRepository;
import com.example.blogdemo.repository.UserRepository;
import com.example.blogdemo.request.CreateUserRequest;
import com.example.blogdemo.request.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public List<UserInfo> getAllUser(){
        return userRepository.findBy(UserInfo.class);
    }
    public List<User> getAllUser1(){
        return userRepository.findAll();
    }

    public UserInfo createUser(CreateUserRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        List<Role> roles = roleRepository.findByIdIn(request.getRoleIds());
        user.setRoleList(roles);
        userRepository.save(user);
        return userRepository.findByEmail(request.getEmail(),UserInfo.class);
    }

    public UserInfo updateUser(Integer id,UpdateUserRequest request){
        User user = userRepository.findById(id).orElseThrow(()-> new BadRequestException("not found"));
        user.setName(request.getName());
        user.setAvatar(request.getAvatar());
        List<Role> roles = roleRepository.findByIdIn(request.getRoleIds());
        user.setRoleList(roles);
        userRepository.save(user);
        return userRepository.findByEmail(user.getEmail(), UserInfo.class);
    }

    public UserInfo findById(Integer id){
        userRepository.findById(id).orElseThrow(()-> new BadRequestException("not found"));
        return userRepository.findById(id, UserInfo.class);
    }




}
