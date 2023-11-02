package com.example.blogdemo.security;


import com.example.blogdemo.entity.User;
import com.example.blogdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user1 = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("user not found" + email));
        return new CustomUserDetails(user1);
    }
}
