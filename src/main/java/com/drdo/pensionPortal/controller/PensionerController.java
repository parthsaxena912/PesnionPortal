package com.drdo.pensionPortal.controller;

import com.drdo.pensionPortal.entity.Pensioner;
import com.drdo.pensionPortal.service.PensionRecordService;
import com.drdo.pensionPortal.service.PensionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pensioners")

public class PensionerController {

    @Autowired
    private PensionerService pensionerService;

    @Autowired
    private PensionRecordService pensionRecordService; // For analytics

    @Autowired
    private SimpMessagingTemplate messagingTemplate; // For WebSocket updates

    // ---------------- GET all pensioners ----------------
    @GetMapping
    public List<Pensioner> getAllPensioners() {
        return pensionerService.getAllPensioners();
    }

    // ---------------- GET pensioner by ID ----------------
    @GetMapping("/{id}")
    public Pensioner getPensionerById(@PathVariable Long id) {
        return pensionerService.getPensionerById(id);
    }

    // ---------------- ADD pensioner ----------------
    @PostMapping
    public Pensioner addPensioner(@RequestBody Pensioner pensioner) {
        Pensioner saved = pensionerService.addPensioner(pensioner);

        // Send live analytics update via WebSocket
        messagingTemplate.convertAndSend("/topic/analytics",
                pensionRecordService.getRealTimeAnalytics());

        return saved; // <-- This returns JSON to Angular
    }

    // ---------------- UPDATE pensioner ----------------
    @PutMapping("/{id}")
    public Pensioner updatePensioner(@PathVariable Long id, @RequestBody Pensioner updatedData) {
        Pensioner existing = pensionerService.getPensionerById(id);

        if (existing == null) {
            throw new RuntimeException("Pensioner not found with ID: " + id);
        }

        // ✅ Update only basic fields
        existing.setName(updatedData.getName());
        existing.setAadharNumber(updatedData.getAadharNumber());
        existing.setBankName(updatedData.getBankName());
        existing.setAccountNumber(updatedData.getAccountNumber());
        existing.setPensionAmount(updatedData.getPensionAmount());

        // ✅ If pensionRecord exists, update it instead of creating new one
        if (existing.getPensionRecord() != null && updatedData.getPensionRecord() != null) {
            existing.getPensionRecord().setPensionAmount(updatedData.getPensionRecord().getPensionAmount());
            existing.getPensionRecord().setPaid(updatedData.getPensionRecord().isPaid());
            existing.getPensionRecord().setCategory(updatedData.getPensionRecord().getCategory());
        }

        Pensioner saved = pensionerService.updatePensioner(id, updatedData);


        // ✅ Send updated analytics
        messagingTemplate.convertAndSend("/topic/analytics", pensionRecordService.getRealTimeAnalytics());

        return saved;
    }

    // ---------------- DELETE pensioner ----------------
    @DeleteMapping("/{id}")
    public void deletePensioner(@PathVariable Long id) {
        pensionerService.deletePensioner(id);

        // Send updated analytics to frontend via WebSocket
        messagingTemplate.convertAndSend("/topic/analytics",
                pensionRecordService.getRealTimeAnalytics());
    }
}
