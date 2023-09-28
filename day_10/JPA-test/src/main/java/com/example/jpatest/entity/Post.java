package com.example.jpatest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor

@Entity
@Table

public class Post {
    @Id
    private String id;
    private String title;

    public Post() {
        this.id = UUID.randomUUID().toString();
    }

}

