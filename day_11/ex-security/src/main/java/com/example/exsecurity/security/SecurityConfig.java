package com.example.exsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/blog").hasAnyRole("AUTHOR","ADMIN","SALE")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN","SALE")
                        .requestMatchers("/profile").hasRole("USER")
                        .requestMatchers("/user-management").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
        http.formLogin(login -> login
                .defaultSuccessUrl("/", true)
                .permitAll()
        );
        http.logout(logout -> logout
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true));
        return http.build();
    }
}
