package com.hdjunction.repository;

import com.hdjunction.entity.PatientVisit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<PatientVisit, Long> {
}
