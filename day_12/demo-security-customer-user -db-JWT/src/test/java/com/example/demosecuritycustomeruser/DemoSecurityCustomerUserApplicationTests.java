package com.example.demosecuritycustomeruser;

import com.example.demosecuritycustomeruser.entity.Role;
import com.example.demosecuritycustomeruser.entity.User;
import com.example.demosecuritycustomeruser.repository.RoleRepository;
import com.example.demosecuritycustomeruser.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class DemoSecurityCustomerUserApplicationTests {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void save() {
		Role role1 = new Role();
		Role role2 = new Role();
		role1.setName("ADMIN");
		role2.setName("USER");
		roleRepository.save(role1);
		roleRepository.save(role2);

//		User user1 = new User(null,"Nguyen A","a@gmail.com",passwordEncoder.encode("123"), List.of(role1,role2));
//		User user2 = new User(null,"Tran B","b@gmail.com",passwordEncoder.encode("123"), List.of(role2));
//		userRepository.save(user1);
//		userRepository.save(user2);
	}

}
