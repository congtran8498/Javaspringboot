package com.example.fastfood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;
    private double totalPrice;
    private double discountValue;
    private Integer quantity;
    private String paymentMethod;
    private String name;
    private String phone;
    private String note;
    private String provine;
    private String district;
    private String ward;
    private String address;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    public enum Status {
        CHO_XAC_NHAN("Chờ xác nhận"),
        DANG_DONG_GOI("Đang đóng gói"),
        DANG_GIAO_HANG("Đang giao hàng"),
        DA_GIAO("Đã giao"),
        HUY_DON("Hủy đơn");
        private final String value;

        Status(String value) {
            this.value = value;
        }

    }
    private Long voucherId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
//    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "order",cascade=CascadeType.ALL)
    private List<OrderDetail> orderDetailList = new ArrayList<>();

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
