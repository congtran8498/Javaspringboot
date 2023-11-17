package com.example.fastfood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private float discount;
    private LocalDateTime expired_at;
    private Status status;

    public enum Status {
        ACTIVE,
        BLOCK;
    }

    public void setExpired_at(LocalDateTime expired_at) {
        this.expired_at = expired_at;
        checkStatus();
    }

    private void checkStatus() {
        if (expired_at != null && expired_at.isBefore(LocalDateTime.now())) {
            status = Status.BLOCK;
        } else {
            status = Status.ACTIVE;
        }
    }


    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    @PreRemove
    protected void onDelete() {
        deletedAt = new Date();
    }
}
