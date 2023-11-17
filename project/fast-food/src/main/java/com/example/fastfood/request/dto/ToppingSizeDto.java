package com.example.fastfood.request.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToppingSizeDto {
    private Long toppingId;
    private Long sizeId;
    private double price;
}
