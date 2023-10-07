package com.example.blogdemo.service;

import com.example.blogdemo.entity.Category;
import com.example.blogdemo.exception.BadRequestException;
import com.example.blogdemo.exception.NotFoundException;
import com.example.blogdemo.model.dto.CategoryDto;
import com.example.blogdemo.repository.CategoryRepository;
import com.example.blogdemo.request.UpsertCategoryRequest;
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


    // xoa category
    public void deleteCategory(Integer id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("not found"));
        if(!category.getBlogList().isEmpty()) throw new BadRequestException("category nay dang duoc su dung");
        categoryRepository.delete(category);
    }

    // tao category
    public Category createCategory(UpsertCategoryRequest request){
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
    public Category updateCategory(Integer id,UpsertCategoryRequest request){
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
}
