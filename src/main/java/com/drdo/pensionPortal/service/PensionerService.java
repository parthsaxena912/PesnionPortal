package com.drdo.pensionPortal.service;

import com.drdo.pensionPortal.entity.Pensioner;
import com.drdo.pensionPortal.entity.PensionRecord;
import com.drdo.pensionPortal.repository.PensionerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PensionerService {

    @Autowired
    private PensionerRepository pensionerRepository;

    // ✅ Get all pensioners
    public List<Pensioner> getAllPensioners() {
        return pensionerRepository.findAll();
    }

    // ✅ Get pensioner by ID
    public Pensioner getPensionerById(Long id) {
        return pensionerRepository.findById(id).orElse(null);
    }

    // ✅ Add new pensioner + create PensionRecord only once
    @Transactional
    public Pensioner addPensioner(Pensioner pensioner) {

        // Create new PensionRecord only if not already linked
        if (pensioner.getPensionRecord() == null) {
            PensionRecord record = new PensionRecord();
            record.setPensionerName(pensioner.getName());
            record.setPensionAmount(pensioner.getPensionAmount());
            record.setPaid(false);
            record.setCategory("Defense");

            // Maintain bidirectional link
            pensioner.setPensionRecord(record);
            record.setPensioner(pensioner);
        }

        return pensionerRepository.save(pensioner);
    }

    // ✅ Update existing pensioner without duplicating record
    @Transactional
    public Pensioner updatePensioner(Long id, Pensioner updatedPensioner) {
        Pensioner existing = pensionerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Pensioner not found with ID: " + id)
        );

        // Update basic pensioner details
        existing.setName(updatedPensioner.getName());
        existing.setAadharNumber(updatedPensioner.getAadharNumber());
        existing.setBankName(updatedPensioner.getBankName());
        existing.setAccountNumber(updatedPensioner.getAccountNumber());
        existing.setPensionAmount(updatedPensioner.getPensionAmount());

        // Update linked PensionRecord safely
        PensionRecord record = existing.getPensionRecord();
        if (record == null) {
            // Create new record if missing
            record = new PensionRecord();
            record.setPensioner(existing);
            existing.setPensionRecord(record);
        }

        record.setPensionerName(updatedPensioner.getName());
        record.setPensionAmount(updatedPensioner.getPensionAmount());
        // Optionally update paid status or category if needed

        // Save existing entity (CascadeType.ALL ensures PensionRecord updates too)
        return pensionerRepository.save(existing);
    }

    // ✅ Delete pensioner (Cascade removes PensionRecord)
    public void deletePensioner(Long id) {
        pensionerRepository.deleteById(id);
    }
}
