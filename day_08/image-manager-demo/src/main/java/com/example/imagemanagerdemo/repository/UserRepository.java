package com.example.imagemanagerdemo.repository;

import com.example.imagemanagerdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
