package com.example.blogdemo.service;

import com.example.blogdemo.entity.Blog;
import com.example.blogdemo.entity.Category;
import com.example.blogdemo.entity.User;
import com.example.blogdemo.exception.NotFoundException;

import com.example.blogdemo.repository.BlogRepository;
import com.example.blogdemo.repository.CategoryRepository;
import com.example.blogdemo.repository.UserRepository;
import com.example.blogdemo.request.UpsertBlogRequest;
import com.github.slugify.Slugify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Blog> findAllBlog(){
        return blogRepository.findAll();
    }

    public Page<Blog> findAll(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page-1,limit, Sort.by("publishedAt").descending());
        return blogRepository.findAll(pageable);
    }

    public Page<Blog> getAllBlogsOfCurrentUser(Integer page, Integer limit){
        User user = userRepository.findById(1).orElseThrow(() ->new NotFoundException("not found"));
        Pageable pageable = PageRequest.of(page-1,limit, Sort.by("publishedAt").descending());
        return blogRepository.findByUser_Id(user.getId(), pageable);
    }

    public Blog createBlog(UpsertBlogRequest request){
        Slugify slugify = Slugify.builder().build();
        User user = userRepository.findById(1).orElseThrow(() ->new NotFoundException("not found"));

        Blog blog = new Blog();
        blog.setTitle(request.getTitle());
        blog.setSlug(slugify.slugify(request.getTitle()));
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        blog.setThumbnail(request.getThumbnail());
        blog.setStatus(request.getStatus());
        blog.setUser(user);

        for (int i = 0; i < request.getCategoryIds().size(); i++) {
            Category category = categoryRepository.findById(request.getCategoryIds().get(i)).orElseThrow(() ->new NotFoundException("not found"));
            blog.getCategoryList().add(category);
        }
        blogRepository.save(blog);
        return blog;
    }

    public Blog findById(Integer id){
        return blogRepository.findById(id).orElseThrow(() -> new NotFoundException("not found"));
    }

    public Blog updateBlog(Integer id, UpsertBlogRequest request){
        Blog blog = blogRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("not found"));
        blog.setTitle(request.getTitle());
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        blog.setThumbnail(request.getThumbnail());
        blog.setStatus(request.getStatus());

        List<Category> categoryList = new ArrayList<>();
        for (int i = 0; i < request.getCategoryIds().size(); i++) {
            Category category = categoryRepository.findById(request.getCategoryIds().get(i)).orElseThrow(() ->new NotFoundException("not found"));
            categoryList.add(category);
        }
        blog.setCategoryList(categoryList);
        blogRepository.save(blog);

        return blog;
    }

    public void deleteBlog(Integer id){
        Blog blog = blogRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("not found"));
        blogRepository.deleteById(id);

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
