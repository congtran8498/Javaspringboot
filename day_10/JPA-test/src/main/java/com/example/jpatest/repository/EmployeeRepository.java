package com.example.jpatest.repository;

import com.example.jpatest.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
//    Tìm tất cả các Employee theo emailAddress và lastName
    List<Employee> findByEmailAddressAndLastName(String emailAddress, String lastName);

//    Tìm tất cả các Employee khác nhau theo firstName hoặc lastName
    List<Employee> findByFirstNameNotOrLastNameNot(String firstName, String lastName);

//    Tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần
    List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);

//    Tìm tất cả các Employee theo fistName không phân biệt hoa thường
    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);


    // Sử dụng sorting
    List<Employee> findByLastName(String lastName, Sort sort);

    // Sử dụng paging
    Page<Employee> findByFirstName(String firstName, Pageable pageable);

    //named query
    List<Employee> findByEmailAddress1(@Param("emailAddress") String emailAddress);

    //query
    @Query("select e from Employee e where e.emailAddress like ?1")
    List<Employee> find_by_email(String emailAddress);

}
