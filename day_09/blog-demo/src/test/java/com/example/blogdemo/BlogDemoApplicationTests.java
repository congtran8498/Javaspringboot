package com.example.blogdemo;

import com.example.blogdemo.entity.*;
import com.example.blogdemo.repository.*;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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
        Role role3 = new Role();
        role1.setName("user");
        role2.setName("admin");
        role3.setName("author");
        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);


        Faker faker = new Faker();
        Random random = new Random();
        List<User> users = List.of(
                new User(null, faker.name().fullName(), faker.internet().emailAddress(), faker.address().streetAddress(), null, List.of(role2, role1)),
                new User(null, faker.name().fullName(), faker.internet().emailAddress(), faker.address().streetAddress(), null, List.of(role1)),
                new User(null, faker.name().fullName(), faker.internet().emailAddress(), faker.address().streetAddress(), null, List.of(role3, role1))
        );

        userRepository.saveAll(users);
    }
    @Test
    void save_blog_category_comment(){
        Random random = new Random();
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();

        List<User> userList = userRepository.findAll();
        List<Category> categories = List.of(
                new Category(null, "Backend",null),
                new Category(null, "Frontend",null),
                new Category(null, "Devops",null),
                new Category(null, "Database",null),
                new Category(null, "Mobile",null),
                new Category(null, "Javascript",null),
                new Category(null, "Java",null),
                new Category(null, "Game",null)
        );
        categoryRepository.saveAll(categories);

        for (int i = 0; i < 20; i++) {

            List<Category> rdList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Category rdCategory = categories.get(random.nextInt(categories.size()));
                if(!rdList.contains(rdCategory)) {
                    rdList.add(rdCategory);
                }
            }

            Blog blog = new Blog();
            blog.setTitle("Blog " +(i+1));
            blog.setContent("Blog content " +(i+1));
            blog.setSlug(slugify.slugify("Blog "+(i+1)));
            blog.setDescription("Blog description "+(i+1));
            blog.setStatus(random.nextInt(2) == 0);
            blog.setUser(userList.get(random.nextInt(userList.size())));
            blog.getCategoryList().addAll(rdList);
            blogRepository.save(blog);


            for (int j = 0; j < 50; j++) {
                Comment comment = new Comment();
                comment.setContent(faker.book().title());
                comment.setBlog(blog);
                comment.setUser(userList.get(random.nextInt(userList.size())));
                commentRepository.save(comment);
            }
        }

    }

}
