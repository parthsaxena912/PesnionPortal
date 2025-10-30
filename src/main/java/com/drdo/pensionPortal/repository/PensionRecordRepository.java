package com.drdo.pensionPortal.repository;

import com.drdo.pensionPortal.entity.PensionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PensionRecordRepository extends JpaRepository<PensionRecord, Long> {

    @Query("SELECT COALESCE(SUM(p.pensionAmount), 0) FROM PensionRecord p WHERE p.paid = true")
    Double getTotalPensionPaid();

    @Query("SELECT COUNT(p) FROM PensionRecord p WHERE p.paid = true")
    Long getTotalPensionersPaid();

    @Query("SELECT COUNT(p) FROM PensionRecord p WHERE p.paid = false")
    Long getTotalPensionersPending();

    @Query("SELECT COALESCE(AVG(p.pensionAmount), 0) FROM PensionRecord p WHERE p.paid = true")
    Double getAveragePension();

    @Query("SELECT COALESCE(SUM(p.pensionAmount), 0) FROM PensionRecord p WHERE p.paid = false")
    Double getPendingPensionAmount();
}
