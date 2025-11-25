package com.careconnect.patientservice.repository;

import com.careconnect.patientservice.entity.Patient;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PatientInMemoryRepository {

    private final Map<Long, Patient> store = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Patient save(Patient patient) {
        if (patient.getId() == null) {
            patient.setId(idGenerator.getAndIncrement());
        }
        store.put(patient.getId(), patient);
        return patient;
    }

    public Optional<Patient> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public void delete(Long id) {
        store.remove(id);
    }

    public List<Patient> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Patient> findByEmail(String email) {
        return store.values().stream()
                .filter(p -> email != null && email.equalsIgnoreCase(p.getEmail()))
                .findFirst();
    }

    public Optional<Patient> findByPhone(String phone) {
        return store.values().stream()
                .filter(p -> phone != null && phone.equalsIgnoreCase(p.getPhone()))
                .findFirst();
    }

    public Optional<Patient> findByNic(String nic) {
        return store.values().stream()
                .filter(p -> nic != null && nic.equalsIgnoreCase(p.getNationalId()))
                .findFirst();
    }

    public List<Patient> searchByName(String name) {
        return store.values().stream()
                .filter(p -> name != null &&
                        (name.equalsIgnoreCase(p.getFirstName()) ||
                                name.equalsIgnoreCase(p.getLastName())))
                .toList();
    }
}
