package com.drdo.pensionPortal.entity;

import jakarta.persistence.*;

@Entity
public class Concern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;  // ✅ use username instead of email
    private String category;
    private String subject;
    private String message;
    private String status = "Pending";

    

	

	// Constructors
    public Concern() {}
    
    public Concern(String username, String category, String subject, String message) {
        this.username = username;
        this.category = category;
        this.subject = subject;
        this.message = message;
        this.status = "Pending";
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getStatus() {
		return status;
	}
    public void setStatus(String status) {
		this.status = status;
	}
    
}
