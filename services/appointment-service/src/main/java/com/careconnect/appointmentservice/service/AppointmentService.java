package com.careconnect.appointmentservice.service;

import com.careconnect.appointmentservice.dto.AppointmentRequestDTO;
import com.careconnect.appointmentservice.dto.AppointmentResponseDTO;
import com.careconnect.appointmentservice.dto.AppointmentStatusUpdateDTO;
import com.careconnect.appointmentservice.dto.AppointmentUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AppointmentService {

    AppointmentResponseDTO create(AppointmentRequestDTO requestDTO);

    AppointmentResponseDTO getById(Long id);

    Page<AppointmentResponseDTO> getByPatient(Long patientId, Pageable pageable);

    Page<AppointmentResponseDTO> getByConsultant(Long consultantId, Pageable pageable);

    Page<AppointmentResponseDTO> getByStatus(String status, Pageable pageable);

    AppointmentResponseDTO update(Long id, AppointmentUpdateDTO updateDTO);

    AppointmentResponseDTO updateStatus(Long id, AppointmentStatusUpdateDTO statusDTO);

    void delete(Long id);

    // NEW method for reporting service
    long countBetweenDates(LocalDate from, LocalDate to);
}