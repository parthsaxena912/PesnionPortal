package com.drdo.pensionPortal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class PensionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pensionerName;
    private double pensionAmount;
    private boolean paid; // renamed from isPaid for clarity
    private String category; // e.g., Defense, Civil, etc.

    // Link back to Pensioner
    @OneToOne(mappedBy = "pensionRecord")
    @JsonBackReference
    private Pensioner pensioner;


    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPensionerName() { return pensionerName; }
    public void setPensionerName(String pensionerName) { this.pensionerName = pensionerName; }

    public double getPensionAmount() { return pensionAmount; }
    public void setPensionAmount(double pensionAmount) { this.pensionAmount = pensionAmount; }

    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = true; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Pensioner getPensioner() { return pensioner; }
    public void setPensioner(Pensioner pensioner) { this.pensioner = pensioner; }
}
