package com.example.fastfood.repository;

import com.example.fastfood.entity.Topping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToppingRepository extends JpaRepository<Topping,Long> {
    List<Topping> findByIdIn(List<Long> ids);
}
