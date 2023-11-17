package com.example.fastfood.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @Size(min = 5, max = 50,message = "tên tối thiểu 5 kí tự và tối đa 50 kí tự")
    @NotEmpty(message = "thiếu tên")
    private String name;

    @Column(unique = true)
    @Size(min = 5, max = 255, message = "email tối thiểu 5 kí tự và tối đa 255 kí tự")
    @NotEmpty(message = "thiếu email")
    @Email(message = "email không hợp lệ")
    private String email;
//
//    @Column(length = 255)
//    @Size(min = 5, max = 50, message = "mật khẩu tối thiểu 5 kí tự và tối đa 50 kí tự")
//    @NotEmpty(message = "thiếu mật khẩu")
    private String password;

//    @Column(length = 12)
//    @Size(max = 12, message = "số điện thoại tối đa 12 kí tự")
//    @NotEmpty(message = "thiếu sdt")
    private String phone;

//    @NotEmpty(message = "thiếu tỉnh")
    private String provine;
//    @NotEmpty(message = "thiếu huyện/thành phố")
    private String district;
//    @NotEmpty(message = "thiếu phường")
    private String ward;
//    @NotEmpty(message = "thiếu địa chỉ")
    private String address;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Boolean isEnabled;

    public enum Status {
        ACTIVE,
        BLOCK
    }

    @ManyToMany(fetch = FetchType.EAGER) // load all role when load user
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =@JoinColumn(name = "role_id")
    )
    private List<Role> roleList = new ArrayList<>();

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
