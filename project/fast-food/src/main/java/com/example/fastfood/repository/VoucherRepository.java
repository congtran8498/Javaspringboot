package com.example.fastfood.repository;

import com.example.fastfood.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher,Long> {
}
