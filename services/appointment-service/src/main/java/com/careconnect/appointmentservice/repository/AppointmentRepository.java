package com.careconnect.appointmentservice.repository;

import com.careconnect.appointmentservice.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Page<Appointment> findByPatientId(Long patientId, Pageable pageable);

    Page<Appointment> findByConsultantId(Long consultantId, Pageable pageable);

    Page<Appointment> findByStatus(String status, Pageable pageable);

    long countByAppointmentDateTimeBetween(OffsetDateTime from, OffsetDateTime to);
}
