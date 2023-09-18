package com.example.imagemanagerdemo;

import com.example.imagemanagerdemo.entity.User;
import com.example.imagemanagerdemo.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImageManagerDemoApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    void save_user() {
        User user = new User();
        user.setName("john");
        userRepository.save(user);
    }
    @Test
    void save_user_1(){
        for (int i = 0; i < 5; i++) {
            User user = new User();
            Faker faker = new Faker();
            user.setName(faker.name().fullName());
            userRepository.save(user);
        }
    }

}
