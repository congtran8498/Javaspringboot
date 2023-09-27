package com.example.blogdemo.service;

import com.example.blogdemo.entity.Blog;
import com.example.blogdemo.request.UpsertBlogRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {
    Page<Blog> findAll(Integer page, Integer limit);
    List<Blog> searchBlog(String term);
    List<Blog> findBlogByCategory(String categoryName);
    Blog findBlogByIdAndSlug(Integer id, String slug);
    List<Blog> findAllBlog();
    Blog createBlog(UpsertBlogRequest request);
    Blog findById(Integer id);
    Blog updateBlog(Integer id, UpsertBlogRequest request);
    void deleteBlog(Integer id);
    Page<Blog> getAllBlogsOfCurrentUser(Integer page, Integer limit);
}
