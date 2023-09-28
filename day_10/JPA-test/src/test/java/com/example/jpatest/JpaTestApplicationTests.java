package com.example.jpatest;

import com.example.jpatest.dto.UserDto;
import com.example.jpatest.entity.Category;
import com.example.jpatest.entity.Post;
import com.example.jpatest.entity.Product;
import com.example.jpatest.entity.User;
import com.example.jpatest.repository.CategoryRepository;
import com.example.jpatest.repository.PostRepository;
import com.example.jpatest.repository.ProductRepository;
import com.example.jpatest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaTestApplicationTests {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void save() {
        Product product1 = new Product();
        product1.setName("product 1");
        Product product = new Product();
        product.setName("product ");

        Category category = new Category();
        category.setName("Category 1");
        Category category2 = new Category();
        category2.setName("Category 2");
        product.setCategory(category);
        categoryRepository.save(category2);
        categoryRepository.save(category);

        productRepository.save(product);
        productRepository.save(product1);

    }
    @Test
    void delete(){
        categoryRepository.deleteById(6L);
    }
    @Test
    void save_post(){
        for (int i = 0; i < 5; i++) {
            Post post = new Post();
            post.setTitle("post" + i);
            postRepository.save(post);
        }
    }
    @Test
    void save_user(){
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setName("user"+i);
            user.setEmail("email"+i);
            user.setPassword("password"+i);
            userRepository.save(user);
        }
        for (UserDto u : userRepository.getAllUserDto()){
            System.out.println(u.getName());
        }
        for (UserDto u : userRepository.getAllUserDtoNQ()){
            System.out.println(u.getName());
        }
    }
}
