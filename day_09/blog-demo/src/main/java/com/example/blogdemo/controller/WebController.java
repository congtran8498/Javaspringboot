package com.example.blogdemo.controller;

import com.example.blogdemo.entity.Blog;
import com.example.blogdemo.model.dto.CategoryDto;
import com.example.blogdemo.service.BlogService;
import com.example.blogdemo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class WebController {
    private final BlogService blogService;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String getHome(Model model) {
        Page<Blog> pageData = blogService.findAll(1, 5);
        List<CategoryDto> categoryDtos = categoryService.getAllCategortDto();

        model.addAttribute("currentPage", 1);
        model.addAttribute("pageData", pageData);
        model.addAttribute("categoryDto", categoryDtos);
        return "web/main";
    }

    @GetMapping("/page/{pageNumber}")
    public String getPage(Model model, @PathVariable Integer pageNumber) {
        Page<Blog> pageData = blogService.findAll(pageNumber, 5);
        List<CategoryDto> categoryDtos = categoryService.getAllCategortDto();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("pageData", pageData);
        model.addAttribute("categoryDto", categoryDtos);
        return "web/page";
    }

    @GetMapping("search")
    public String searchBlog() {
        return "web/search";
    }

    @GetMapping("categories")
    public String getAllCategory(Model model) {
        List<CategoryDto> categoryDtos = categoryService.getAllCategory();
        model.addAttribute("categoryList", categoryDtos);
        return "web/tag";
    }

    @GetMapping("categories/{categoryName}")
    public String getBlogsOfCategory(Model model, @PathVariable String categoryName) {
        List<Blog> blogs = blogService.findBlogByCategory(categoryName);
        model.addAttribute("blogs",blogs);
        model.addAttribute("categoryName",categoryName);
        return "web/tagDetail";
    }

    @GetMapping("blogs/{blogId}/{blogSlug}")
    public String getBlogDetail(Model model, @PathVariable Integer blogId, @PathVariable String blogSlug) {
        Blog blog = blogService.findBlogByIdAndSlug(blogId,blogSlug);
        model.addAttribute("blog", blog);
        return "web/blogdetail";
    }


    // admin view
    @GetMapping("/admin")
    public String getAdminPage(){
        return "admin/blog/index";
    }

    @GetMapping("/admin/create")
    public String createBlogPage(){
        return "admin/blog/blog-create";
    }

    @GetMapping("/admin/detail")
    public String getDetailBlogPage(){
        return "admin/blog/blog-detail";
    }

    @GetMapping("/admin/blogs")
    public String getAllBlogPage(){
        return "admin/blog/blog-index";
    }

    @GetMapping("/admin/blogs-yourelf")
    public String getOwnsBlogPage(){
        return "admin/blog/blog-yourself";
    }

}
