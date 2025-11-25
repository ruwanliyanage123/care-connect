package com.careconnect.consultantservice.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ConsultantUpdateDTO {

    private String firstName;
    private String lastName;

    @Email
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
}
