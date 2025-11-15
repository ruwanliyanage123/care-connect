package com.careconnect.consultantservice.service;

import com.careconnect.consultantservice.dto.AppointmentRequestDTO;
import com.careconnect.consultantservice.dto.AppointmentResponseDTO;
import com.careconnect.consultantservice.dto.AppointmentStatusUpdateDTO;
import com.careconnect.consultantservice.dto.AppointmentUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppointmentService {
    AppointmentResponseDTO create(AppointmentRequestDTO requestDTO);
    AppointmentResponseDTO getById(Long id);
    Page<AppointmentResponseDTO> getByPatient(Long patientId, Pageable pageable);
    Page<AppointmentResponseDTO> getByConsultant(Long consultantId, Pageable pageable);
    Page<AppointmentResponseDTO> getByStatus(String status, Pageable pageable);
    AppointmentResponseDTO update(Long id, AppointmentUpdateDTO updateDTO);
    AppointmentResponseDTO updateStatus(Long id, AppointmentStatusUpdateDTO statusDTO);
    void delete(Long id);
}
