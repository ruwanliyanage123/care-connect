package com.careconnect.consultantservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consultant {

    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String gender;
    private String specialization;
    private Integer yearsOfExperience;
    private String registrationNumber;
    private String languages;
    private String bio;
    private Double rating;
    private Boolean active;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
