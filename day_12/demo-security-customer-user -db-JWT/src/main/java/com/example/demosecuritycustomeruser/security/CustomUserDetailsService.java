package com.example.demosecuritycustomeruser.security;

import com.example.demosecuritycustomeruser.entity.User;
import com.example.demosecuritycustomeruser.repository.UserRepository;
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
//        User user = userRepository.findAll().stream()
//                .filter(u -> u.getEmail().equals(email))
//                .findFirst()
//                .orElseThrow(() -> new UsernameNotFoundException("user not found with emai: "+email ));
        User user1 = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("user not found" + email));
        return new CustomUserDetails(user1);
    }
}
