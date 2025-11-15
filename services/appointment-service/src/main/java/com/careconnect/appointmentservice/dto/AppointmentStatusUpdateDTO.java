package com.careconnect.appointmentservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AppointmentStatusUpdateDTO {
    @NotBlank
    private String status;
}
