package com.careconnect.consultantservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PatientRequestDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private LocalDate dateOfBirth;

    private String gender;

    @Email
    private String email;

    private String phone;

    @NotBlank
    private String nationalId;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String country;

    private String bloodGroup;

    private String allergies;

    private String chronicDiseases;

    private String emergencyContactName;

    private String emergencyContactPhone;
}
