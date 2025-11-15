package com.careconnect.consultantservice.service.impl;

import com.careconnect.consultantservice.dto.AppointmentRequestDTO;
import com.careconnect.consultantservice.dto.AppointmentResponseDTO;
import com.careconnect.consultantservice.dto.AppointmentStatusUpdateDTO;
import com.careconnect.consultantservice.dto.AppointmentUpdateDTO;
import com.careconnect.consultantservice.entity.Appointment;
import com.careconnect.consultantservice.exception.AppointmentNotFoundException;
import com.careconnect.consultantservice.mapper.AppointmentMapper;
import com.careconnect.consultantservice.repository.AppointmentRepository;
import com.careconnect.consultantservice.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public AppointmentResponseDTO create(AppointmentRequestDTO requestDTO) {
        Appointment entity = appointmentMapper.toEntity(requestDTO);
        Appointment saved = appointmentRepository.save(entity);
        return appointmentMapper.toResponse(saved);
    }

    @Override
    public AppointmentResponseDTO getById(Long id) {
        Appointment entity = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(id));
        return appointmentMapper.toResponse(entity);
    }

    @Override
    public Page<AppointmentResponseDTO> getByPatient(Long patientId, Pageable pageable) {
        return appointmentRepository.findByPatientId(patientId,pageable)
                .map(appointmentMapper::toResponse);
    }

    @Override
    public Page<AppointmentResponseDTO> getByConsultant(Long consultantId, Pageable pageable) {
        return appointmentRepository.findByConsultantId(consultantId,pageable)
                .map(appointmentMapper::toResponse);
    }

    @Override
    public Page<AppointmentResponseDTO> getByStatus(String status, Pageable pageable) {
        return appointmentRepository.findByStatus(status,pageable)
                .map(appointmentMapper::toResponse);
    }

    @Override
    public AppointmentResponseDTO update(Long id, AppointmentUpdateDTO updateDTO) {
        Appointment entity = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(id));
        appointmentMapper.applyUpdate(updateDTO,entity);
        Appointment saved = appointmentRepository.save(entity);
        return appointmentMapper.toResponse(saved);
    }

    @Override
    public AppointmentResponseDTO updateStatus(Long id, AppointmentStatusUpdateDTO statusDTO) {
        Appointment entity = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(id));
        entity.setStatus(statusDTO.getStatus());
        Appointment saved = appointmentRepository.save(entity);
        return appointmentMapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        Appointment entity = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(id));
        appointmentRepository.delete(entity);
    }
}
