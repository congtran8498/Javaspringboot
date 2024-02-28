package com.example.fastfood.repository;

import com.example.fastfood.entity.Order;
import com.example.fastfood.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUser_IdOrderByOrderDateDesc(Long id);

    List<Order> findByStatus(Order.Status status);
    Page<Order> findByUser(User user, Pageable pageable);
}
