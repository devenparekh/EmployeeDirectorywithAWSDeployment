package com.example.demoapplication.student.studentRepository;

import com.example.demoapplication.student.studentdefinition.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    Optional<Student> findStudentByEmail(String email);

    Optional<Student> findStudentByName(String name);

    List<Student> findStudentById(Long id);
}
