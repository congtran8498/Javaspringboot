package com.example.demojpa;

import com.example.demojpa.entity.Employee;
import com.example.demojpa.entity.Student;
import com.example.demojpa.repository.EmployeeRepository;
import com.example.demojpa.repository.StudentRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@SpringBootTest
class DemoJpaApplicationTests {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

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



    // Employeee
    @Test
    void save_all_employee() {
        Faker faker = new Faker();
        Random rd = new Random();
        List<String> departments = List.of("A", "B", "C", "D"); // Danh sách phòng ban
        Date from = new Date(1204095742779L); // Ngày bắt đầu
        Date to = new Date(1604095742779L); // Ngày kết thúc
        for (int i = 0; i < 30; i++) {
            Employee employee = new Employee(
                    null,
                    faker.name().fullName(),
                    departments.get(rd.nextInt(departments.size())),
                    (long) faker.number().numberBetween(1000, 5000),
                    covertToLocalDate(faker.date().between(from, to))
            );
            employeeRepository.save(employee);
        }
    }

    private LocalDate covertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Test
    void find_by_department(){
        List<Employee> employeeList1 = new ArrayList<>();
        List<Employee> employeeList2 = new ArrayList<>();
        List<Employee> employeeList3 = new ArrayList<>();
        employeeList1 = employeeRepository.findByDepartment("A");
        employeeList2 = employeeRepository.findByDepartmentJPQL("A");
        employeeList3 = employeeRepository.findByDepartmentNQ("A");
        System.out.println(employeeList1);
        System.out.println(employeeList2);
        System.out.println(employeeList3);
    }
    @Test
    void find_by_salary(){
        List<Employee> employeeList1 = new ArrayList<>();
        List<Employee> employeeList2 = new ArrayList<>();
        List<Employee> employeeList3 = new ArrayList<>();
        employeeList1 = employeeRepository.findBySalaryGreaterThan(4000);
        employeeList2 = employeeRepository.findBySalaryJPQL(4000);
        employeeList3 = employeeRepository.findBySalaryNQ(4000);
        System.out.println(employeeList1);
        System.out.println(employeeList2);
        System.out.println(employeeList3);
    }
    @Test
    void find_Employee_by_name(){
        List<Employee> employeeList1 = new ArrayList<>();
        List<Employee> employeeList2 = new ArrayList<>();
        List<Employee> employeeList3 = new ArrayList<>();
        employeeList1 = employeeRepository.findByNameContainingIgnoreCase("da");
        employeeList2 = employeeRepository.findByNameJPQL("da");
        employeeList3 = employeeRepository.findByNameNQ("da");
        System.out.println(employeeList1);
        System.out.println(employeeList2);
        System.out.println(employeeList3);
    }
}
