package com.careconnect.appointmentservice.mapper;

import com.careconnect.appointmentservice.dto.AppointmentRequestDTO;
import com.careconnect.appointmentservice.dto.AppointmentResponseDTO;
import com.careconnect.appointmentservice.dto.AppointmentUpdateDTO;
import com.careconnect.appointmentservice.entity.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public Appointment toEntity(AppointmentRequestDTO dto) {
        if (dto == null) return null;
        return Appointment.builder()
                .patientId(dto.getPatientId())
                .consultantId(dto.getConsultantId())
                .appointmentDateTime(dto.getAppointmentDateTime())
                .reason(dto.getReason())
                .notes(dto.getNotes())
                .status("SCHEDULED")
                .build();
    }

    public void applyUpdate(AppointmentUpdateDTO dto, Appointment appointment) {
        if (dto == null || appointment == null) return;
        if (dto.getConsultantId() != null) appointment.setConsultantId(dto.getConsultantId());
        if (dto.getAppointmentDateTime() != null) appointment.setAppointmentDateTime(dto.getAppointmentDateTime());
        if (dto.getReason() != null) appointment.setReason(dto.getReason());
        if (dto.getNotes() != null) appointment.setNotes(dto.getNotes());
        if (dto.getStatus() != null) appointment.setStatus(dto.getStatus());
    }

    public AppointmentResponseDTO toResponse(Appointment entity) {
        if (entity == null) return null;
        return AppointmentResponseDTO.builder()
                .id(entity.getId())
                .patientId(entity.getPatientId())
                .consultantId(entity.getConsultantId())
                .appointmentDateTime(entity.getAppointmentDateTime())
                .status(entity.getStatus())
                .reason(entity.getReason())
                .notes(entity.getNotes())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
