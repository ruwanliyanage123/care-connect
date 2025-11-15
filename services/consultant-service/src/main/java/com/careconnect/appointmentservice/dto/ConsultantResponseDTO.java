package com.careconnect.appointmentservice.dto;

import lombok.Builder;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
@Builder
public class ConsultantResponseDTO {
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
