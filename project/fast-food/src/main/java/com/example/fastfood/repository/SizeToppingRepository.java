package com.example.fastfood.repository;

import com.example.fastfood.entity.Size;
import com.example.fastfood.entity.SizeTopping;
import com.example.fastfood.entity.Topping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SizeToppingRepository extends JpaRepository<SizeTopping,Long> {
    List<SizeTopping> findBySize_Id(Long id);
    SizeTopping findBySizeAndTopping(Size size, Topping topping);
}
