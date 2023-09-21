package com.example.blogdemo;

import com.example.blogdemo.entity.*;
import com.example.blogdemo.repository.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
class BlogDemoApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ImageRepository imageRepository;


    @Test
    void save_user_role(){
        Role role1 = new Role();
        Role role2 = new Role();
        role1.setName("user");
        role2.setName("admin");
        roleRepository.save(role1);
        roleRepository.save(role2);


        Faker faker = new Faker();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setName(faker.name().fullName());
            user.setEmail(faker.internet().emailAddress());
            user.setAddress(faker.address().streetAddress());
            user.setPassword(faker.internet().password());
            user.getRoleList().add(roleRepository.findById(random.nextInt(2) + 1).get());
            userRepository.save(user);
        }
    }
    @Test
    void save_blog_category_comment(){
        Random random = new Random();
        Faker faker = new Faker();
        List<User> userList = userRepository.findAll();
        for (int i = 0; i < 5; i++) {
            Category category = new Category();
            category.setName(faker.book().genre());
            categoryRepository.save(category);
        }
        for (int i = 0; i < 5; i++) {
            Blog blog = new Blog();
            blog.setTitle(faker.book().title());
            blog.setContent(faker.book().title());
            blog.setSlug(faker.internet().slug());
            blog.setThumbnail(faker.internet().avatar());
            blog.setDescription(faker.book().title());
            blog.setStatus("active");
            blog.setUser(userRepository.findById(random.nextInt(5) + 1).get());
            blog.getCategoryList().add(categoryRepository.findById(random.nextInt(5) + 1).get());
            blogRepository.save(blog);

            User user = userList.get(i);
            for (int j = 0; j < 10; j++) {
                Comment comment = new Comment();
                comment.setContent(faker.book().title());
                comment.setBlog(blog);
                comment.setUser(user);
                commentRepository.save(comment);
            }
        }

    }

}
