package com.careconnect.consultantservice.repository;

import com.careconnect.consultantservice.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Page<Appointment> findByPatientId(Long patientId, Pageable pageable);
    Page<Appointment> findByConsultantId(Long consultantId, Pageable pageable);
    Page<Appointment> findByStatus(String status, Pageable pageable);
}
