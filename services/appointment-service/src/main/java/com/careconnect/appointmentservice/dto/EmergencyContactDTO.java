package com.careconnect.appointmentservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmergencyContactDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
}
