package com.example.fastfood.repository;

import com.example.fastfood.entity.Product;
import com.example.fastfood.entity.ShoppingCartDetail;
import com.example.fastfood.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartDetailRepository extends JpaRepository<ShoppingCartDetail,Long> {
    ShoppingCartDetail findByProductAndSizeAndToppingId(Product product, Size size,Long toppingId);

}
