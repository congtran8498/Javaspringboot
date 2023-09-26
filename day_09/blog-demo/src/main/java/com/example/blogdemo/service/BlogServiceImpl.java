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

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    private BlogRepository blogRepository;


    public Page<Blog> findAll(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page-1,limit, Sort.by("publishedAt").descending());
        return blogRepository.findByStatus(true, pageable);
    }

    public List<Blog> searchBlog(String term){
        return blogRepository.findByTitleContainingIgnoreCaseAndStatusTrue(term);
    }

    public List<Blog> findBlogByCategory(String categoryName){
        return blogRepository.findByCategoryName(categoryName);
    }



    public Blog findBlogByIdAndSlug(Integer id, String slug){
        return blogRepository.findByIdAndSlugAndStatusTrue(id,slug)
                .orElseThrow(() -> new NotFoundException("blog not found"));
    }
}
