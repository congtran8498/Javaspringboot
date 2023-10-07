package com.example.blogdemo.repository;

import com.example.blogdemo.entity.Category;
import com.example.blogdemo.model.dto.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findByBlogList_Status(Boolean status);

//     Lấy danh sách category và số lượng bài viết áp dụng
    @Query("""
        select new com.example.blogdemo.model.dto.CategoryDto(c.id,c.name, COUNT (b.id))
        FROM Category c
        JOIN c.blogList b
        WHERE b.status = true
        GROUP BY c.id,c.name
""")
    List<CategoryDto> getAllCategoryDto();

    List<Category> findByIdIn(List<Integer> list);

    Category findByName(String name);

}
