package com.careconnect.appointmentservice.repository;

import com.careconnect.appointmentservice.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByNationalId(String nationalId);

    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByPhone(String phone);

    List<Patient> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName,
            String lastName
    );
}
