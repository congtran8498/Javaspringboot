package com.example.demojpa;

import com.example.demojpa.entity.Employee;
import com.example.demojpa.entity.Student;
import com.example.demojpa.repository.StudentRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DemoJpaApplicationTests {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void save_student() {
        Student student = new Student(null,"Công","cong@gmail.com",18);
        studentRepository.save(student);
    }
    @Test
    void save_students(){
        Student student1 = new Student(null,"Công1","cong1@gmail.com",20);
        Student student2 = new Student(null,"Công2","cong2@gmail.com",22);
        studentRepository.saveAll(List.of(student1,student2));
    }
    @Test
    void save_all_student(){
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            Student student = new Student(
                    null,
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.number().numberBetween(18,40)
            );
            studentRepository.save(student);
        }
    }

    @Test
    void find_by_email(){
        Student student = new Student();
        student = studentRepository.findByEmail("cong1@gmail.com").orElse(null);
        System.out.println(student);
    }
    @Test
    void find_by_name(){
        List<Student> students = new ArrayList<>();
        students = studentRepository.findByNameContainingIgnoreCase("công");
        System.out.println(students);
    }

//    @Test
//    void save_all_employee(){
//        Faker faker = new Faker();
//
//        for(int i = 0; i < 10; i ++){
//            Employee employee = new Employee(
//                    null,
//                    faker.name().fullName(),
//                    "sales",
//                    faker.number().numberBetween(1000,5000),
//                    faker.date().between(new Date(2020, Calendar.FEBRUARY,1), new Date(2023,Calendar.FEBRUARY,1));
//            );
//        }
//    }
}
