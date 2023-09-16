package com.example.demojpa.repository;

import com.example.demojpa.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByEmail(String email);

    List<Student> findByNameContainingIgnoreCase(String name);
    Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Student> findByAgeGreaterThan(Integer age);
    Page<Student> findByAgeGreaterThan(Integer age, Pageable pageable);

    // Sap xep
    List<Student> findByAgeGreaterThanOrderByAgeAsc(Integer age);

    @Query(nativeQuery = true, value = "select * from student where age > ?1 order by age asc ")
    List<Student> findByAgeGreaterThanOrderByAgeAscNQ(Integer age);

    List<Student> findByAgeGreaterThan(Integer age, Sort sort);

    //

    List<Student> findByAgeBetween(Integer minAge, Integer maxAge);

    Optional<Student> findByEmailAndName(String email, String name);

    boolean existsByEmail(String email);

    long countByName(String name);

    Optional<Student> findFirstByAgeLessThan(Integer age);

    // Update Student
    @Modifying
    @Transactional
    @Query(value = "UPDATE student SET name = ?2 WHERE id = ?1", nativeQuery = true)
    void update_name(Integer id, String name);

    // Delete Student
    @Modifying
    @Transactional
    @Query(value = "DELETE from student WHERE email = ?1" , nativeQuery = true)
    void delete_student(String email);

    // Phan trang
    @Query(value = "select * from student", nativeQuery = true,
            countQuery = "select count(*) from student")
    Page<Student> getAllStudent(Pageable pageable);
}
