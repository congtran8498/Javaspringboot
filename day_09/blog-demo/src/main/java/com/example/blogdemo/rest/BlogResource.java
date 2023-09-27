package com.example.blogdemo.rest;

import com.example.blogdemo.entity.Blog;
import com.example.blogdemo.request.UpsertBlogRequest;
import com.example.blogdemo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/admin/blogs")
public class BlogResource {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public ResponseEntity<?> getAllBlog(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize){
        return ResponseEntity.ok(blogService.findAll(page,pageSize));
    }
    @GetMapping("/own-blogs")
    public ResponseEntity<?> getAllBlogsOfCurrentUser(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(blogService.getAllBlogsOfCurrentUser(page, limit));
    }

    @PostMapping
    public ResponseEntity<?> createBlog(@RequestBody UpsertBlogRequest request){
        return new ResponseEntity<>(blogService.createBlog(request), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBlogById(@PathVariable Integer id){
        return ResponseEntity.ok(blogService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBlog(@PathVariable Integer id, @RequestBody UpsertBlogRequest request){
        return ResponseEntity.ok(blogService.updateBlog(id,request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer id){
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}
