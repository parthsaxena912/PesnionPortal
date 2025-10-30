package com.drdo.pensionPortal.controller;



import com.drdo.pensionPortal.entity.Insurance;
import com.drdo.pensionPortal.service.InsuranceService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/insurance")
@CrossOrigin("*")
public class InsuranceController {

    private final InsuranceService service;

    public InsuranceController(InsuranceService service) {
        this.service = service;
    }

    @PostMapping("/apply")
    public Insurance apply(@RequestBody Insurance application) {
        return service.applyForInsurance(application);
    }

    @GetMapping("/all")
    public List<Insurance> getAllApplications() {
        return service.getAllApplications();
    }

    @PutMapping("/{id}/status")
    public Insurance updateStatus(@PathVariable Long id, @RequestParam String status) {
        return service.updateStatus(id, status);
    }

    @GetMapping("/user/{userId}")
    public List<Insurance> getUserApplications(@PathVariable Long userId) {
        return service.getApplicationsByUser(userId);
    }
}
