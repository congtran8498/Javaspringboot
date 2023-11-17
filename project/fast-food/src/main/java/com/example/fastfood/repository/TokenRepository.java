package com.example.fastfood.repository;

import com.example.fastfood.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findByToken(String token);
}
