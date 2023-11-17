package com.example.fastfood.repository;

import com.example.fastfood.entity.Product;
import com.example.fastfood.entity.ProductSize;
import com.example.fastfood.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSizeRepository extends JpaRepository<ProductSize,Long> {
    List<ProductSize> findByProduct_Id(Long id);
    ProductSize findByProductAndSize(Product product, Size size);
    List<ProductSize> findByProduct(Product product);
}
