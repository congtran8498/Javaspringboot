package com.example.relationshipdemo.repository;

import com.example.relationshipdemo.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Integer> {
}
