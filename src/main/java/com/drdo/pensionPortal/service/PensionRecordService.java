package com.drdo.pensionPortal.service;

import com.drdo.pensionPortal.entity.PensionRecord;
import com.drdo.pensionPortal.repository.PensionRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PensionRecordService {

    @Autowired
    private PensionRecordRepository pensionRecordRepository;

    // ---------------- GET all PensionRecords ----------------
    public List<PensionRecord> getAllRecords() {
        return pensionRecordRepository.findAll();
    }

    // ---------------- GET single PensionRecord by ID ----------------
    public PensionRecord getRecordById(Long id) {
        Optional<PensionRecord> record = pensionRecordRepository.findById(id);
        return record.orElse(null);
    }

    // ---------------- GET real-time analytics ----------------
    public Map<String, Object> getRealTimeAnalytics() {
        Map<String, Object> analytics = new HashMap<>();

        Double totalPensionPaid = pensionRecordRepository.getTotalPensionPaid();
        Double pendingPensionAmount = pensionRecordRepository.getPendingPensionAmount();
        Long pensionersPaid = pensionRecordRepository.getTotalPensionersPaid();
        Long pensionersPending = pensionRecordRepository.getTotalPensionersPending();
        Double averagePension = pensionRecordRepository.getAveragePension();

        Double totalPensionAmount = totalPensionPaid + pendingPensionAmount;

        analytics.put("totalPensionPaid", totalPensionPaid);
        analytics.put("pendingPensionAmount", pendingPensionAmount);
        analytics.put("pensionersPaid", pensionersPaid);
        analytics.put("pensionersPending", pensionersPending);
        analytics.put("averagePension", averagePension);
        analytics.put("totalPensionAmount", totalPensionAmount);

        return analytics;
    }

    // ---------------- Mark PensionRecord as paid ----------------
    public PensionRecord markRecordAsPaid(Long id) {
        PensionRecord record = pensionRecordRepository.findById(id).orElse(null);
        if (record != null) {
            record.setPaid(true);
            pensionRecordRepository.save(record);
        }
        return record;
    }
}
