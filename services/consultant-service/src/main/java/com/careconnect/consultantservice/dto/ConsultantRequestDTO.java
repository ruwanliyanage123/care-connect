package com.careconnect.consultantservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ConsultantRequestDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
    private String phone;
    private String gender;
    @NotBlank
    private String specialization;
    private Integer yearsOfExperience;
    private String registrationNumber;
    private String languages;
    private String bio;
}
