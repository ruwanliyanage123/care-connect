package com.careconnect.medicationservice.mapper;

import com.careconnect.medicationservice.dto.*;
import com.careconnect.medicationservice.entity.Medication;
import org.springframework.stereotype.Component;

@Component
public class MedicationMapper {

    public Medication toEntity(MedicationRequestDTO dto) {
        return Medication.builder()
                .medicationName(dto.getMedicationName())
                .brand(dto.getBrand())
                .category(dto.getCategory())
                .dosage(dto.getDosage())
                .price(dto.getPrice())
                .stockCount(dto.getStockCount())
                .manufacturer(dto.getManufacturer())
                .expiryDate(dto.getExpiryDate())
                .build();
    }

    public void applyUpdate(MedicationUpdateDTO dto, Medication medication) {
        if (dto.getMedicationName() != null) medication.setMedicationName(dto.getMedicationName());
        if (dto.getBrand() != null) medication.setBrand(dto.getBrand());
        if (dto.getCategory() != null) medication.setCategory(dto.getCategory());
        if (dto.getDosage() != null) medication.setDosage(dto.getDosage());
        if (dto.getPrice() != null) medication.setPrice(dto.getPrice());
        if (dto.getStockCount() != null) medication.setStockCount(dto.getStockCount());
        if (dto.getManufacturer() != null) medication.setManufacturer(dto.getManufacturer());
        if (dto.getExpiryDate() != null) medication.setExpiryDate(dto.getExpiryDate());
    }

    public MedicationResponseDTO toResponseDTO(Medication medication) {
        return MedicationResponseDTO.builder()
                .id(medication.getId())
                .medicationName(medication.getMedicationName())
                .brand(medication.getBrand())
                .category(medication.getCategory())
                .dosage(medication.getDosage())
                .price(medication.getPrice())
                .stockCount(medication.getStockCount())
                .manufacturer(medication.getManufacturer())
                .expiryDate(medication.getExpiryDate())
                .createdAt(medication.getCreatedAt())
                .updatedAt(medication.getUpdatedAt())
                .build();
    }
}
