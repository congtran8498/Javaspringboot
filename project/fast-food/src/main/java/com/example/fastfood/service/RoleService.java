package com.example.fastfood.service;

import com.example.fastfood.entity.Role;
import com.example.fastfood.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }
}
