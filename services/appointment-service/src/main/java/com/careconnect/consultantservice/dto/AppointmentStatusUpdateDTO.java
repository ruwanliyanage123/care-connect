package com.careconnect.consultantservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AppointmentStatusUpdateDTO {
    @NotBlank
    private String status;
}
