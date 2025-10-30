package com.drdo.pensionPortal.service;




import org.springframework.stereotype.Service;

import com.drdo.pensionPortal.entity.Concern;
import com.drdo.pensionPortal.repository.ConcernRepository;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class ConcernService {

    @Autowired
    private ConcernRepository concernRepository;

    // Save a new concern
    public Concern saveConcern(Concern concern) {
        // You can add any business logic here if needed
        return concernRepository.save(concern);
    }

    // Fetch all concerns for admin
    public List<Concern> getAllConcerns() {
        return concernRepository.findAll();
    }

    // Optional: Fetch concerns by username
    public List<Concern> getConcernsByUsername(String username) {
        return concernRepository.findAll().stream()
                .filter(c -> c.getUsername().equals(username))
                .toList();
    }
    public Concern updateStatus(Long id, String status) {
        Concern concern = concernRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Concern not found with id " + id));
        concern.setStatus(status);
        return concernRepository.save(concern);
    }
}
