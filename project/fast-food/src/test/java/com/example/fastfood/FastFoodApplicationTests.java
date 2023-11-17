package com.example.fastfood;

import com.example.fastfood.entity.Role;
import com.example.fastfood.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FastFoodApplicationTests {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    void createRole() {
        Role r1 = new Role();
        r1.setName("ADMIN");
        Role r2 = new Role();
        r2.setName("USER");
        roleRepository.save(r1);
        roleRepository.save(r2);
    }

}
