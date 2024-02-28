package com.example.fastfood.repository;

import com.example.fastfood.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByIdIn(List<Long> ids);
    List<Category> findByStatus(Category.Status status);
}
