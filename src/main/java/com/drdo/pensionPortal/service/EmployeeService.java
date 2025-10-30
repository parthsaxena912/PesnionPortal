package com.drdo.pensionPortal.service;


import com.drdo.pensionPortal.entity.Employee;
import com.drdo.pensionPortal.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Add new employee
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Update employee
    public Employee updateEmployee(Long id, Employee updated) {
        return employeeRepository.findById(id)
                .map(emp -> {
                    emp.setName(updated.getName());
                    emp.setDesignation(updated.getDesignation());
                    emp.setDepartment(updated.getDepartment());
                    emp.setEmail(updated.getEmail());
                    emp.setBasicSalary(updated.getBasicSalary());
                    emp.setPassword(updated.getPassword());
                    return employeeRepository.save(emp);
                }).orElse(null);
    }

    // Delete employee
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}

