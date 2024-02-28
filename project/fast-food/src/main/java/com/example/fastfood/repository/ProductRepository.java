package com.example.fastfood.repository;

import com.example.fastfood.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByStatus(Product.Status status, Pageable pageable);

}
