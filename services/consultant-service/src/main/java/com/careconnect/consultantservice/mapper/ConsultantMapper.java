package com.careconnect.consultantservice.mapper;

import com.careconnect.consultantservice.dto.ConsultantRequestDTO;
import com.careconnect.consultantservice.dto.ConsultantResponseDTO;
import com.careconnect.consultantservice.dto.ConsultantUpdateDTO;
import com.careconnect.consultantservice.entity.Consultant;
import org.springframework.stereotype.Component;

@Component
public class ConsultantMapper {
    public Consultant toEntity(ConsultantRequestDTO dto) {
        if (dto == null) return null;
        return Consultant.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .gender(dto.getGender())
                .specialization(dto.getSpecialization())
                .yearsOfExperience(dto.getYearsOfExperience())
                .registrationNumber(dto.getRegistrationNumber())
                .languages(dto.getLanguages())
                .bio(dto.getBio())
                .build();
    }

    public void applyUpdate(ConsultantUpdateDTO dto, Consultant consultant) {
        if (dto == null || consultant == null) return;
        if (dto.getFirstName() != null) consultant.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) consultant.setLastName(dto.getLastName());
        if (dto.getEmail() != null) consultant.setEmail(dto.getEmail());
        if (dto.getPhone() != null) consultant.setPhone(dto.getPhone());
        if (dto.getGender() != null) consultant.setGender(dto.getGender());
        if (dto.getSpecialization() != null) consultant.setSpecialization(dto.getSpecialization());
        if (dto.getYearsOfExperience() != null) consultant.setYearsOfExperience(dto.getYearsOfExperience());
        if (dto.getRegistrationNumber() != null) consultant.setRegistrationNumber(dto.getRegistrationNumber());
        if (dto.getLanguages() != null) consultant.setLanguages(dto.getLanguages());
        if (dto.getBio() != null) consultant.setBio(dto.getBio());
        if (dto.getActive() != null) consultant.setActive(dto.getActive());
    }

    public ConsultantResponseDTO toResponseDTO(Consultant consultant) {
        if (consultant == null) return null;
        return ConsultantResponseDTO.builder()
                .id(consultant.getId())
                .firstName(consultant.getFirstName())
                .lastName(consultant.getLastName())
                .email(consultant.getEmail())
                .phone(consultant.getPhone())
                .gender(consultant.getGender())
                .specialization(consultant.getSpecialization())
                .yearsOfExperience(consultant.getYearsOfExperience())
                .registrationNumber(consultant.getRegistrationNumber())
                .languages(consultant.getLanguages())
                .bio(consultant.getBio())
                .rating(consultant.getRating())
                .active(consultant.getActive())
                .createdAt(consultant.getCreatedAt())
                .updatedAt(consultant.getUpdatedAt())
                .build();
    }
}
