package com.example.usermanager.service;

import com.example.usermanager.dao.UserDAO;
import com.example.usermanager.dto.UserDto;
import com.example.usermanager.entity.UserEntity;
import com.example.usermanager.exception.BadRequestException;
import com.example.usermanager.exception.ResouceNotFoundException;
import com.example.usermanager.model.User;
import com.example.usermanager.repository.UserRepository;
import com.example.usermanager.request.CreateUserRequest;
import com.example.usermanager.request.UpdateAvatarRequest;
import com.example.usermanager.request.UpdatePasswordRequest;
import com.example.usermanager.request.UpdateUserRequest;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final UserRepository userRepository;

    public UserServiceImpl(UserDAO userDAO, UserRepository userRepository) {
        this.userDAO = userDAO;
        this.userRepository = userRepository;
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

    // JPA
    //0. tao user
    public void save_all_user(){
        if(userRepository.findAll().isEmpty()){
            Faker faker = new Faker();
            for(int i = 0; i < 20; i++){
                UserEntity user = new UserEntity(
                        null,
                        faker.name().fullName(),
                        faker.internet().emailAddress(),
                        faker.phoneNumber().cellPhone(),
                        faker.address().streetAddress(),
                        faker.avatar().image(),
                        faker.internet().password()
                );
                userRepository.save(user);
            }
        }
    }


    // 1. Lay danh sach User
    public List<UserDto> get_all_user(){
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(this::mapToDto1)
                .collect(Collectors.toList());
    }

    // 2. Lay danh sach User theo ten
    public List<UserDto> get_by_name(String name){
        List<UserEntity> userEntities = userRepository.findByNameContainingIgnoreCase(name);
        return userEntities.stream().map(this::mapToDto1)
                .collect(Collectors.toList());
    }

    // 3. Tim kiem theo Id
    public UserDto get_by_id(Integer id){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Not found user"));
        return mapToDto1(user);
    }

    // 4. Tao moi user
    public UserDto create_user(CreateUserRequest request){
        UserEntity user = new UserEntity();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setPassword(request.getPassword());
        user.setAvatar(null);
        userRepository.save(user);
        return mapToDto1(user);
    }

    // 5. Cap nhat thong tin user
    public UserDto update_user(Integer id, UpdateUserRequest request){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Not found user"));
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        userRepository.save(user);
        return mapToDto1(user);
    }

    // 6. Xoa User
    public void delete_user(Integer id){
        userRepository.deleteById(id);
    }

    // 7. update avatar
    public void update_avatar(Integer id, UpdateAvatarRequest request){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Not found user"));
        user.setAvatar(request.getAvatar());
        userRepository.save(user);
    }

    // 8. thay doi mat khau
    public void  update_password(Integer id, UpdatePasswordRequest request){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Not found user"));
        if(!user.getPassword().equals(request.getOldPassWord())){
            throw new BadRequestException("Mật khẩu cũ không chính xác");
        }
        if(user.getPassword().equals(request.getNewPassWord())){
            throw new BadRequestException("Mật khẩu mới không được trùng với mật khẩu cũ");
        }
        user.setPassword(request.getNewPassWord());
        userRepository.save(user);
    }

    // 9. Quen mat khau
    public String forgot_password(Integer id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Not found user"));
        String newPassWord = generateRandomString();
        user.setPassword(newPassWord);
        userRepository.save(user);
        return newPassWord;
    }
    private String generateRandomString() {
        Random random = new Random();
        int length = 10;
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }


    private UserDto mapToDto1(UserEntity user) {
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
