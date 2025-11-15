package com.careconnect.medicationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicationRequestDTO {

    @NotBlank
    private String medicationName;

    @NotBlank
    private String brand;

    @NotBlank
    private String category;

    @NotBlank
    private String dosage;

    @NotNull
    private Double price;

    @NotNull
    private Integer stockCount;

    @NotBlank
    private String manufacturer;

    private LocalDate expiryDate;
}
