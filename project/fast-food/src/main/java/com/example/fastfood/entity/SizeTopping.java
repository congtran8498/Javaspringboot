package com.example.fastfood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "size_topping")
public class SizeTopping implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "size_id")
    @JsonIgnore
    private Size size;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topping_id")
    private Topping topping;

    @Column(name = "price")
    private Double price;
}
