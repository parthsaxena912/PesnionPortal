package com.drdo.pensionPortal.entity;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;  // Link to Pensioner (user)
    private String insuranceType;
    private String status; // Pending, Approved, Rejected
    private LocalDateTime appliedAt;
    private String remarks; // Optional notes by admin

    // Constructor
    public Insurance() {
        this.appliedAt = LocalDateTime.now();
        this.status = "Pending";
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getInsuranceType() { return insuranceType; }
    public void setInsuranceType(String insuranceType) { this.insuranceType = insuranceType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDateTime appliedAt) { this.appliedAt = appliedAt; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
