package com.drdo.pensionPortal.repository;



import com.drdo.pensionPortal.entity.Pensioner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PensionerRepository extends JpaRepository<Pensioner, Long> {
}
