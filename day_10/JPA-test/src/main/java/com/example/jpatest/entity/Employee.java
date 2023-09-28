package com.example.jpatest.entity;

import jakarta.persistence.*;

@Entity
@Table
@NamedQuery(name = "Employee.findByEmailAddress1",
        query = "select e from Employee e WHERE e.emailAddress like :emailAddress")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String emailAddress;
    private String firstName;
    private String lastName;
}
