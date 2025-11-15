package com.careconnect.patientservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmergencyContactDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
}
