package com.drdo.pensionPortal.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "pensioners")
public class Pensioner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String aadharNumber;
    private String bankName;
    private String accountNumber;

    @Transient
    private double pensionAmount; // optional: keep for input; stored in PensionRecord

    // Link to PensionRecord
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "record_id")
    @JsonManagedReference
    private PensionRecord pensionRecord;

    public Pensioner() {}

    public Pensioner(String name, String aadharNumber, String bankName, String accountNumber, double pensionAmount) {
        this.name = name;
        this.aadharNumber = aadharNumber;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.pensionAmount = pensionAmount;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAadharNumber() { return aadharNumber; }
    public void setAadharNumber(String aadharNumber) { this.aadharNumber = aadharNumber; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public double getPensionAmount() { return pensionAmount; }
    public void setPensionAmount(double pensionAmount) { this.pensionAmount = pensionAmount; }

    public PensionRecord getPensionRecord() { return pensionRecord; }
    public void setPensionRecord(PensionRecord pensionRecord) {
        this.pensionRecord = pensionRecord;
        if (pensionRecord != null) {
            pensionRecord.setPensioner(this); // maintain bidirectional link
        }
    }
}
