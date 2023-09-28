package com.example.jpatest.repository;

import com.example.jpatest.dto.UserDto;
import com.example.jpatest.dto.UserInfo;
import com.example.jpatest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("""
       select new com.example.jpatest.dto.UserDto(u.id,u.name,u.email)
       from User u
""")
    List<UserDto> getAllUserDto();

    @Query(nativeQuery = true, name = "getAllUserDtoNQ")
    List<UserDto> getAllUserDtoNQ();

    UserInfo findByNameContainingIgnoreCase(String name);
}
