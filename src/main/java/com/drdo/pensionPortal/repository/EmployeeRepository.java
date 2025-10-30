package com.drdo.pensionPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.drdo.pensionPortal.entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}
