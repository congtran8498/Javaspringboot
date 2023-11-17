package com.example.fastfood.repository;

import com.example.fastfood.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {
    List<Image> findByUser_IdOrderByCreatedAtDesc(Long id);
    List<Image> findByIdIn(List<Long> ids);
}
