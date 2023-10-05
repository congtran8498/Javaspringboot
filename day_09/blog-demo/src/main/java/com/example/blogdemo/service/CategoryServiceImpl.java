package com.example.blogdemo.service;

import com.example.blogdemo.entity.Category;
import com.example.blogdemo.model.dto.CategoryDto;
import com.example.blogdemo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDto> getAllCategortDto(){
        List<CategoryDto> list = categoryRepository.getAllCategoryDto();
        list.sort(new Comparator<CategoryDto>() {
            @Override
            public int compare(CategoryDto o1, CategoryDto o2) {
                return (int) (o2.getUsed() - o1.getUsed());
            }
        });
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            categoryDtos.add(list.get(i));
        }
        return categoryDtos;
    }

    public List<CategoryDto> getAllCategory(){
        return categoryRepository.getAllCategoryDto();
    }
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}
