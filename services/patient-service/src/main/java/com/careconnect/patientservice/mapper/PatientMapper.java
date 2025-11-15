package com.careconnect.appointmentservice.mapper;

import com.careconnect.appointmentservice.dto.PatientRequestDTO;
import com.careconnect.appointmentservice.dto.PatientResponseDTO;
import com.careconnect.appointmentservice.dto.PatientUpdateDTO;
import com.careconnect.appointmentservice.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public Patient toEntity(PatientRequestDTO dto) {
        if (dto == null) return null;

        return Patient.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .dateOfBirth(dto.getDateOfBirth())
                .gender(dto.getGender())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .nationalId(dto.getNationalId())
                .addressLine1(dto.getAddressLine1())
                .addressLine2(dto.getAddressLine2())
                .city(dto.getCity())
                .country(dto.getCountry())
                .bloodGroup(dto.getBloodGroup())
                .allergies(dto.getAllergies())
                .chronicDiseases(dto.getChronicDiseases())
                .emergencyContactName(dto.getEmergencyContactName())
                .emergencyContactPhone(dto.getEmergencyContactPhone())
                .build();
    }

    public void applyUpdate(PatientUpdateDTO dto, Patient patient) {
        if (dto == null || patient == null) return;

        if (dto.getFirstName() != null) patient.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) patient.setLastName(dto.getLastName());
        if (dto.getDateOfBirth() != null) patient.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getGender() != null) patient.setGender(dto.getGender());
        if (dto.getEmail() != null) patient.setEmail(dto.getEmail());
        if (dto.getPhone() != null) patient.setPhone(dto.getPhone());
        if (dto.getNationalId() != null) patient.setNationalId(dto.getNationalId());
        if (dto.getAddressLine1() != null) patient.setAddressLine1(dto.getAddressLine1());
        if (dto.getAddressLine2() != null) patient.setAddressLine2(dto.getAddressLine2());
        if (dto.getCity() != null) patient.setCity(dto.getCity());
        if (dto.getCountry() != null) patient.setCountry(dto.getCountry());
        if (dto.getBloodGroup() != null) patient.setBloodGroup(dto.getBloodGroup());
        if (dto.getAllergies() != null) patient.setAllergies(dto.getAllergies());
        if (dto.getChronicDiseases() != null) patient.setChronicDiseases(dto.getChronicDiseases());
        if (dto.getEmergencyContactName() != null) patient.setEmergencyContactName(dto.getEmergencyContactName());
        if (dto.getEmergencyContactPhone() != null) patient.setEmergencyContactPhone(dto.getEmergencyContactPhone());
    }

    public PatientResponseDTO toResponseDTO(Patient patient) {
        if (patient == null) return null;

        return PatientResponseDTO.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .dateOfBirth(patient.getDateOfBirth())
                .gender(patient.getGender())
                .email(patient.getEmail())
                .phone(patient.getPhone())
                .nationalId(patient.getNationalId())
                .addressLine1(patient.getAddressLine1())
                .addressLine2(patient.getAddressLine2())
                .city(patient.getCity())
                .country(patient.getCountry())
                .bloodGroup(patient.getBloodGroup())
                .allergies(patient.getAllergies())
                .chronicDiseases(patient.getChronicDiseases())
                .emergencyContactName(patient.getEmergencyContactName())
                .emergencyContactPhone(patient.getEmergencyContactPhone())
                .createdAt(patient.getCreatedAt())
                .updatedAt(patient.getUpdatedAt())
                .build();
    }
}
