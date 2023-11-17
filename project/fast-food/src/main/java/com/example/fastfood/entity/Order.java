package com.example.fastfood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private Float totalPrice;
    private String name;
    private String phone;
    private String note;
    private String provine;
    private String district;
    private String ward;
    private String address;
    private Float discountValue;
    private Status status;

    @Getter
    private enum Status {
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

    @OneToOne
    @JoinColumn(name = "discountCode_id")
    private Voucher voucher;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetailList;

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
