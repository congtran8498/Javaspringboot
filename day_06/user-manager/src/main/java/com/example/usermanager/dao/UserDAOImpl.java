package com.example.usermanager.dao;

import com.example.usermanager.database.UserDB;
import com.example.usermanager.dto.UserDto;
import com.example.usermanager.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class UserDAOImpl implements UserDAO{
    @Override
    public List<User> findAllUser() {
        return UserDB.userList;
    }

    @Override
    public User saveUser(User user) {
        user.setId(createId());
        UserDB.userList.add(user);
        return user;
    }
    private Integer createId() {
        List<User> userList = UserDB.userList;
        return userList.stream()
                .map(User::getId)
                .mapToInt(Integer::valueOf)
                .max().orElse(0) + 1;
    }

    @Override
    public void deleleUser(User user) {
        UserDB.userList.remove(user);
    }

    @Override
    public List<User> findUserByName(String name) {
        return UserDB.userList.stream()
                .filter(u -> u.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @Override
    public Optional<User> findUserById(Integer id) {
        return UserDB.userList.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public String forgotPassword(User user) {
        String newPassword = generateRandomString();
        user.setPassword(newPassword);
        return newPassword;
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
}
