package com.example.fastfood.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequest {
    private Long productId;
    private Long sizeId;
    private Long toppingId;
    private Integer quantity;

}
