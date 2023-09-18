package com.example.relationshipdemo.repository;

import com.example.relationshipdemo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
