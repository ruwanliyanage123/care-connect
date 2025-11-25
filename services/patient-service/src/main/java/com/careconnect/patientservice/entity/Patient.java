package com.careconnect.patientservice.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    private Long id;

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

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
