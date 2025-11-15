package com.careconnect.appointmentservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Builder
public class AppointmentResponseDTO {
    private Long id;
    private Long patientId;
    private Long consultantId;
    private LocalDateTime appointmentDateTime;
    private String status;
    private String reason;
    private String notes;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
