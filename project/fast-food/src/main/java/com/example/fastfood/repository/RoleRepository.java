package com.example.fastfood.repository;

import com.example.fastfood.entity.Category;
import com.example.fastfood.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
    List<Role> findByIdIn(List<Long> ids);
}
