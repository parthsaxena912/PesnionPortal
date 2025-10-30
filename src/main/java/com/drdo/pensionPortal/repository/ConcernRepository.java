package com.drdo.pensionPortal.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.drdo.pensionPortal.entity.Concern;

public interface ConcernRepository extends JpaRepository<Concern, Long> {}
