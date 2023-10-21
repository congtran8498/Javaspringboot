package com.example.demosecuritycustomeruser.security;

import com.example.demosecuritycustomeruser.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private List<User> userList = new ArrayList<>();

    public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        userList.add(new User(1,"Nguyen A", "a@gmail.com", passwordEncoder.encode("123"), List.of("USER","ADMIN")));
        userList.add(new User(2,"Tran B", "b@gmail.com", passwordEncoder.encode("123"), List.of("USER")));
        userList.add(new User(3,"Bui C", "c@gmail.com", passwordEncoder.encode("123"), List.of("USER")));
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userList.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("user not found with emai: "+email ));
        return new CustomUserDetails(user);
    }
}
