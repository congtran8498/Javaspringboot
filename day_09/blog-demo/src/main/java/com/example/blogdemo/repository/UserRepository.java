package com.example.blogdemo.repository;

import com.example.blogdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByRoleList_NameIn(List<String> roles);

    <T> T findByEmail(String email, Class<T> type);
}
