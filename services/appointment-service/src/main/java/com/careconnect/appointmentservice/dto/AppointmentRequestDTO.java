package com.careconnect.appointmentservice.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentRequestDTO {
    @NotNull
    private Long patientId;
    @NotNull
    private Long consultantId;
    @NotNull
    @Future
    private LocalDateTime appointmentDateTime;
    private String reason;
    private String notes;
}
