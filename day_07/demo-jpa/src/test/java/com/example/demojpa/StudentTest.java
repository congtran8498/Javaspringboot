package com.example.demojpa;

import com.example.demojpa.entity.Student;
import com.example.demojpa.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class StudentTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void update_name(){
        studentRepository.update_name(4,"Nguyen thi C");
        Student std = studentRepository.findById(4).orElse(null);
        System.out.println(std);
        Assertions.assertNotNull(std);
        Assertions.assertEquals(std.getName(), "Nguyen thi C");
    }
    @Test
    void delete(){
        studentRepository.delete_student("cong@gmail.com");
    }

    @Test
    void pagingTest(){
        Pageable pageable = PageRequest.of(0,5);
        Page<Student> page = studentRepository.findAll(pageable);
        page.getContent().forEach(System.out::println);
        System.out.println();

        Page<Student> page1 = studentRepository.getAllStudent(pageable);
        page1.getContent().forEach(System.out::println);
    }

    @Test
    void sortingTest(){
        List<Student> studentList = studentRepository.findByAgeGreaterThanOrderByAgeAsc(35);
        studentList.forEach(System.out::println);
        System.out.println();

        List<Student> studentList1 = studentRepository.findByAgeGreaterThanOrderByAgeAscNQ(35);
        studentList.forEach(System.out::println);
        System.out.println();

        List<Student> studentList2 = studentRepository.findByAgeGreaterThan(35, Sort.by("age").ascending());
        studentList.forEach(System.out::println);
    }
}
