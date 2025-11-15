package com.careconnect.medicationservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Builder
public class MedicationResponseDTO {
    private Long id;
    private String medicationName;
    private String brand;
    private String category;
    private String dosage;
    private Double price;
    private Integer stockCount;
    private String manufacturer;
    private LocalDate expiryDate;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
