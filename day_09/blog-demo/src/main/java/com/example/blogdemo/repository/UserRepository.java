package com.example.blogdemo.repository;

import com.example.blogdemo.entity.User;
import com.example.blogdemo.model.dto.CategoryDto;
import com.example.blogdemo.model.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByRoleList_NameIn(List<String> roles);

    <T> T findByEmail(String email, Class<T> type);

    <T> List<T> findBy(Class<T> type);
    Optional<User> findByEmail(String email);
    <T> T findById(Integer id, Class<T> type);
}
