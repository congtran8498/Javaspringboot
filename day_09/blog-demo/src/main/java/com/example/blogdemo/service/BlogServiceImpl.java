package com.example.blogdemo.service;

import com.example.blogdemo.entity.Blog;
import com.example.blogdemo.entity.Category;
import com.example.blogdemo.exception.NotFoundException;
import com.example.blogdemo.model.dto.CategoryDto;
import com.example.blogdemo.repository.BlogRepository;
import com.example.blogdemo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BlogServiceImpl {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Blog> findAll(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page-1,limit, Sort.by("publishedAt").descending());
        return blogRepository.findByStatus(true, pageable);
    }

    public List<Blog> searchBlog(String term){
        return blogRepository.findByTitleContainingIgnoreCase(term);
    }

    public List<Blog> findBlogByCategory(String categoryName){
        return blogRepository.findByCategoryName(categoryName);
    }

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

    public Blog findBlogByIdAndSlug(Integer id, String slug){
        return blogRepository.findByIdAndSlugAndStatusTrue(id,slug)
                .orElseThrow(() -> new NotFoundException("blog not found"));
    }
}
