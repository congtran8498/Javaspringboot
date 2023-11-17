package com.example.fastfood.request.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePizzaRequestDto {
    private String name;
    private String description;
    private List<Long> categoryIds;
    private Long imageId;
    private List<ProductSizeDto> productSizeDtoList;
}
