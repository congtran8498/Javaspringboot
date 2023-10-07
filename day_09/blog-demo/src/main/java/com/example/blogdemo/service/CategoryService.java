package com.example.blogdemo.service;

import com.example.blogdemo.entity.Category;
import com.example.blogdemo.model.dto.CategoryDto;
import com.example.blogdemo.request.UpsertCategoryRequest;

import java.util.List;

public interface CategoryService {
     List<CategoryDto> getAllCategortDto();
     List<CategoryDto> getAllCategory();
     List<Category> findAll();
     void deleteCategory(Integer id);
     Category createCategory(UpsertCategoryRequest request);
     Category updateCategory(Integer id,UpsertCategoryRequest request);

}
