package com.careconnect.medicationservice.repository;

import com.careconnect.medicationservice.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    List<Medication> findByMedicationNameContainingIgnoreCase(String name);
    List<Medication> findByCategoryIgnoreCase(String category);
}
