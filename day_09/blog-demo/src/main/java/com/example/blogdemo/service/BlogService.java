package com.example.blogdemo.service;

import com.example.blogdemo.entity.Blog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {
    Page<Blog> findAll(Integer page, Integer limit);
    List<Blog> searchBlog(String term);
    List<Blog> findBlogByCategory(String categoryName);
    Blog findBlogByIdAndSlug(Integer id, String slug);
}
