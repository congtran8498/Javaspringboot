package com.example.fastfood.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInforRequest {
    private String name;
    private String phone;
    private String address;
    private String note;
    private String paymentMethod;
    private double discountValue;
    private Long voucherId;
    private String province;
    private String district;
    private String ward;
}
