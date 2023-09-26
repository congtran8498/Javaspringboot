package com.example.blogdemo.controller;

import com.example.blogdemo.service.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("api/v1")
public class BlogController {
    @Autowired
    private BlogServiceImpl blogService;

    //1. Lấy danh sách blog
    @GetMapping("/blogs")
    public ResponseEntity<?> getAllBlog(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize){
        return ResponseEntity.ok(blogService.findAll(page,pageSize));
    }

    //2. Tìm kiếm blog
    @GetMapping("/search")
    public ResponseEntity<?> searchBlog(@RequestParam String term){
        return ResponseEntity.ok(blogService.searchBlog(term));
    }

    //3. Lấy danh sách category
    //4. Lấy danh sách category được sử dụng nhiều nhất
    //5. Lấy danh sách bài viết áp dụng category
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<?> getBlogByCategory(@PathVariable String categoryName){
        return ResponseEntity.ok(blogService.findBlogByCategory(categoryName));
    }

    //6. Lấy chi tiết bài viết
    @GetMapping("/blogs/{blogId}/{blogSlug}")
    public ResponseEntity<?> getBlogByIdAndSlug(@PathVariable Integer blogId, @PathVariable String blogSlug){
        return ResponseEntity.ok(blogService.findBlogByIdAndSlug(blogId,blogSlug));
    }
}
