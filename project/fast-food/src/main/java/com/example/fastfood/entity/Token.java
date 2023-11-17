package com.example.fastfood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt; // thời gian tạo

    @Column(name = "expired_at")
    private LocalDateTime expiredAt; // thời gian hết hạn

    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt; // thời gian xác thực
}
