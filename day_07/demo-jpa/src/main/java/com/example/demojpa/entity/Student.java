package com.example.demojpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false, columnDefinition = "varchar(100)")
    private String name;
    @Column(name="email", unique = true, nullable = false)
    private String email;
    private Integer age;
}
