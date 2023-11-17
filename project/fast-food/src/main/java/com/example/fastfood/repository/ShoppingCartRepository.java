package com.example.fastfood.repository;

import com.example.fastfood.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
}
