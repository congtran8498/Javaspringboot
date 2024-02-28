package com.example.fastfood.repository;

import com.example.fastfood.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoucherRepository extends JpaRepository<Voucher,Long> {
    Optional<Voucher> findByCode(String code);
}
