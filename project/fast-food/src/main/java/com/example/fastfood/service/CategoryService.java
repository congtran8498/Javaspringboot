package com.example.fastfood.service;

import com.example.fastfood.entity.Category;
import com.example.fastfood.exception.BadRequestException;
import com.example.fastfood.exception.NotFoundException;
import com.example.fastfood.repository.CategoryRepository;
import com.example.fastfood.request.UpsertRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    // lay danh sach category
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    // tao category
    public Category createCategory(UpsertRequest request){
        List<Category> categoryList = categoryRepository.findAll();
        for (Category value : categoryList) {
            if (value.getName().equalsIgnoreCase(request.getName().trim())) throw new BadRequestException("category da ton tai");
        }
        Category category = new Category();
        category.setName(request.getName());
        categoryRepository.save(category);
        return category;
    }

    // cap nhat category
    public Category updateCategory(Long id, UpsertRequest request){
        List<Category> categoryList = categoryRepository.findAll();
        for (Category value : categoryList) {
            if (value.getName().equalsIgnoreCase(request.getName().trim())) throw new BadRequestException("category da ton tai");
        }

        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("not found"));
        category.setName(request.getName());
        categoryRepository.save(category);
        return category;
    }
    // xoa category
    public void deleteCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("not found"));
        if(!category.getProductList().isEmpty()) throw new BadRequestException("category nay dang duoc su dung");
        categoryRepository.delete(category);
    }
}
