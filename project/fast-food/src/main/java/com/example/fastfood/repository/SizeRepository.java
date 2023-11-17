package com.example.fastfood.repository;

import com.example.fastfood.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size,Long> {
    List<Size> findByIdIn(List<Long> ids);
}
