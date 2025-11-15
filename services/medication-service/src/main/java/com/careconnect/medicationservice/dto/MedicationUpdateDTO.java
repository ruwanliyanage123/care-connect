package com.careconnect.medicationservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicationUpdateDTO {
    private String medicationName;
    private String brand;
    private String category;
    private String dosage;
    private Double price;
    private Integer stockCount;
    private String manufacturer;
    private LocalDate expiryDate;
}
