package com.example.fastfood.service;

import com.example.fastfood.entity.Product;
import com.example.fastfood.entity.ProductSize;
import com.example.fastfood.repository.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSizeService {
    @Autowired
    private ProductSizeRepository productSizeRepository;
    public Page<ProductSize> findAll(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page-1,limit);
        return productSizeRepository.findAll(pageable);
    }
    public List<ProductSize> getAll(){
        return productSizeRepository.findAll();
    }
}
