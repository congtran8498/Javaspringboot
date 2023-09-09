package com.example.usermanager.repository;

import com.example.usermanager.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    // 1. Tim kiem user theo ten
    List<UserEntity> findByNameContainingIgnoreCase(String name);


}
