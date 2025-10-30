package com.drdo.pensionPortal.controller;

import com.drdo.pensionPortal.entity.PensionRecord;
import com.drdo.pensionPortal.service.PensionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/pension-records")
@CrossOrigin(origins = "http://localhost:4200")
public class PensionRecordController {

    @Autowired
    private PensionRecordService pensionRecordService;

    // ✅ Get all pension records
    @GetMapping
    public List<PensionRecord> getAllPensionRecords() {
        return pensionRecordService.getAllRecords();
    }

    // ✅ Get analytics data
    @GetMapping("/analytics")
    public Map<String, Object> getAnalytics() {
        return pensionRecordService.getRealTimeAnalytics();
    }

    // ✅ Get a single record
    @GetMapping("/{id}")
    public PensionRecord getPensionRecordById(@PathVariable Long id) {
        return pensionRecordService.getRecordById(id);
    }

    // ✅ Update "paid" status
    @PutMapping("/{id}/pay")
    public PensionRecord markAsPaid(@PathVariable Long id) {
        return pensionRecordService.markRecordAsPaid(id);
    }
}
