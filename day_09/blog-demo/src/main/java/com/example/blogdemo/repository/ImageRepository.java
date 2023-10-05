package com.example.blogdemo.repository;

import com.example.blogdemo.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Integer> {
    List<Image> findByUser_IdOrderByCreatedAtDesc(Integer id);
}
