package com.example.relationshipdemo.repository;

import com.example.relationshipdemo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
