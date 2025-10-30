package com.drdo.pensionPortal.entity;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String designation;
    private String department;
    private String email;
    private double basicSalary;
    private String password;

    // Constructors
    public Employee() {}

    public Employee(String name, String designation, String department, String email, double basicSalary, String password) {
        this.name = name;
        this.designation = designation;
        this.department = department;
        this.email = email;
        this.basicSalary = basicSalary;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getBasicSalary() { return basicSalary; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
