package com.drdo.pensionPortal.service;



import com.drdo.pensionPortal.entity.Insurance;
import com.drdo.pensionPortal.repository.InsuranceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InsuranceService {

    private final InsuranceRepository repository;

    public InsuranceService(InsuranceRepository repository) {
        this.repository = repository;
    }

    public Insurance applyForInsurance(Insurance application) {
        return repository.save(application);
    }

    public List<Insurance> getAllApplications() {
        return repository.findAll();
    }

    public List<Insurance> getApplicationsByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public Insurance updateStatus(Long id, String status) {
        Insurance app = repository.findById(id).orElseThrow();
        app.setStatus(status);
        return repository.save(app);
    }
}

