package com.drdo.pensionPortal.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.drdo.pensionPortal.entity.Concern;
import com.drdo.pensionPortal.service.ConcernService;

import java.util.List;

@RestController
@RequestMapping("/api/concerns")
@CrossOrigin(origins = "http://localhost:4200")
public class ConcernController {

    @Autowired
    private ConcernService concernService;

    @PostMapping
    public Concern raiseConcern(@RequestBody Concern concern) {
        return concernService.saveConcern(concern);
    }

    @GetMapping
    public List<Concern> getAllConcerns() {
        return concernService.getAllConcerns();
    }

    @PatchMapping("/{id}/status")
    public Concern updateStatus(@PathVariable Long id, @RequestParam String status) {
        return concernService.updateStatus(id, status);
    }
}

