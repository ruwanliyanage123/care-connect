package com.careconnect.patientservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientUpdateDTO {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String email;
    private String phone;
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
