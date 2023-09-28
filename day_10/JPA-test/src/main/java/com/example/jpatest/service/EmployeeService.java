package com.example.jpatest.service;

import com.example.jpatest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.jpatest.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findEmployeesByLastName(String lastName) {
        Sort sort = Sort.by("firstName").ascending();
        return employeeRepository.findByLastName(lastName, sort);
    }

    public Page<Employee> findEmployeesByFirstName(String firstName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findByFirstName(firstName, pageable);
    }
}
