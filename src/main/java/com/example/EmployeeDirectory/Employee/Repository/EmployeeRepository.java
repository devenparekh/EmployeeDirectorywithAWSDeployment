package com.example.EmployeeDirectory.Employee.Repository;

import com.example.EmployeeDirectory.Employee.Definition.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findEmployeeByEmail(String email);

    Optional<Employee> findEmployeeByName(String name);

    Employee findEmployeeById(Long id);
}
