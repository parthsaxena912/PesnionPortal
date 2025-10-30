package com.drdo.pensionPortal.repository;



import com.drdo.pensionPortal.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    List<Insurance> findByUserId(Long userId);
}
