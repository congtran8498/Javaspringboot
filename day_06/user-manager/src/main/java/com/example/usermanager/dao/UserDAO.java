package com.example.usermanager.dao;
import com.example.usermanager.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> findAllUser();
    User saveUser(User user);
    void deleleUser(User user);
    List<User> findUserByName(String name);
    Optional<User> findUserById(Integer id);
    String forgotPassword(User user);
}
