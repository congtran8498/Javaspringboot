package com.example.blogdemo.rest;

import com.example.blogdemo.request.UpsertCategoryRequest;
import com.example.blogdemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/categories")
public class CategoryResource {
    @Autowired
    private CategoryService categoryService;
    //Tao moi
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody UpsertCategoryRequest request){
        return new ResponseEntity<>(categoryService.createCategory(request), HttpStatus.CREATED);
    }
    //Cap nhat
    @PutMapping("{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id,@RequestBody UpsertCategoryRequest request){
        return ResponseEntity.ok(categoryService.updateCategory(id,request));
    }


    //Xoa
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
