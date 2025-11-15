package com.careconnect.consultantservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentUpdateDTO {
    private Long consultantId;
    private LocalDateTime appointmentDateTime;
    private String reason;
    private String notes;
    private String status;
}
