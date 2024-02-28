package com.example.fastfood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_category",
            joinColumns =@JoinColumn( name = "product_id"),
            inverseJoinColumns =@JoinColumn(name = "category_id"))

    private List<Category> categoryList = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ProductSize> productSizeList = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade=CascadeType.ALL)
    @JsonIgnore
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    public enum Status {
        ACTIVE("Đang sử dụng"),
        BLOCK("Hết hàng");
        private final String value;

        Status(String value) {
            this.value = value;
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
