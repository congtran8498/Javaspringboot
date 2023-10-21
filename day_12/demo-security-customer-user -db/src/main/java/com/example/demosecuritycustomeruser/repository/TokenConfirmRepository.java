package com.example.demosecuritycustomeruser.repository;

import com.example.demosecuritycustomeruser.entity.TokenConfirm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenConfirmRepository extends JpaRepository<TokenConfirm,Integer> {
    Boolean existsByToken(String token);
    Optional<TokenConfirm> findByToken(String token);

}
