package com.example.blogdemo.controller;

import com.example.blogdemo.entity.Blog;
import com.example.blogdemo.model.dto.CategoryDto;
import com.example.blogdemo.service.BlogServiceImpl;
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
    private final BlogServiceImpl blogService;

    @GetMapping("/")
    public String getHome(Model model) {
        Page<Blog> pageData = blogService.findAll(1, 5);
        List<CategoryDto> categoryDtos = blogService.getAllCategortDto();

        model.addAttribute("currentPage", 1);
        model.addAttribute("pageData", pageData);
        model.addAttribute("categoryDto", categoryDtos);
        return "main";
    }

    @GetMapping("/page/{pageNumber}")
    public String getPage(Model model, @PathVariable Integer pageNumber) {
        Page<Blog> pageData = blogService.findAll(pageNumber, 5);
        List<CategoryDto> categoryDtos = blogService.getAllCategortDto();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("pageData", pageData);
        model.addAttribute("categoryDto", categoryDtos);
        return "page";
    }

    @GetMapping("search")
    public String searchBlog() {
        return "search";
    }

    @GetMapping("categories")
    public String getAllCategory(Model model) {
        List<CategoryDto> categoryDtos = blogService.getAllCategory();
        model.addAttribute("categoryList", categoryDtos);
        return "tag";
    }

    @GetMapping("categories/{categoryName}")
    public String getBlogsOfCategory(Model model, @PathVariable String categoryName) {
        List<Blog> blogs = blogService.findBlogByCategory(categoryName);
        model.addAttribute("blogs",blogs);
        model.addAttribute("categoryName",categoryName);
        return "tagDetail";
    }

    @GetMapping("blogs/{blogId}/{blogSlug}")
    public String getBlogDetail(Model model, @PathVariable Integer blogId, @PathVariable String blogSlug) {
        Blog blog = blogService.findBlogByIdAndSlug(blogId,blogSlug);
        model.addAttribute("blog", blog);
        return "blogdetail";
    }

}
