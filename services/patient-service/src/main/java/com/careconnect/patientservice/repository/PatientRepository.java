package com.careconnect.patientservice.repository;

import com.careconnect.patientservice.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {

    Optional<Patient> findByNationalId(String nationalId);

    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByPhone(String phone);

    List<Patient> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName,
            String lastName
    );
}
