package com.example.fastfood.service;

import com.example.fastfood.entity.Product;
import com.example.fastfood.entity.User;
import com.example.fastfood.exception.BadRequestException;
import com.example.fastfood.exception.NotFoundException;
import com.example.fastfood.repository.RoleRepository;
import com.example.fastfood.repository.UserRepository;
import com.example.fastfood.request.ChangePasswordRequest;
import com.example.fastfood.request.UpdateInforUserRequest;
import com.example.fastfood.request.UpdateUserInfor;
import com.example.fastfood.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    public User getUser(){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails.getUser();
    }

    public Object changePassword(ChangePasswordRequest request){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        boolean isOldPasswordCorrect = passwordEncoder.matches(request.getOldPass(), user.getPassword());
        if(isOldPasswordCorrect){
            user.setPassword(passwordEncoder.encode(request.getNewPass()));
            userRepository.save(user);
        }else{
            return new BadRequestException("Mật khẩu cũ không chính xác");
        }
        return "Đổi mật khẩu thành công";
    }
    public User updateInfor(UpdateInforUserRequest request){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        String url = "/api/v1/admin/image/"+request.getAvatarId();
        user.setAvatarUrl(url);
        userRepository.save(user);
        return user;
    }
    public Page<User> getAllUser(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page-1,limit);
        return userRepository.findAll(pageable);
    }
    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new NotFoundException("không tìm thấy người dùng này"));
    }
    public User updateUser(UpdateUserInfor request,Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Không có user nào"));
        user.setName(request.getName());
        user.setRoleList(roleRepository.findByIdIn(request.getRoleIds()));
        user.setIsEnabled(request.getIsEnabled());

        return userRepository.save(user);
    }
}
