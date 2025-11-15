package com.careconnect.medicationservice.service;

import com.careconnect.medicationservice.dto.*;

import java.util.List;

public interface MedicationService {
    MedicationResponseDTO create(MedicationRequestDTO dto);
    MedicationResponseDTO update(Long id, MedicationUpdateDTO dto);
    MedicationResponseDTO getById(Long id);
    List<MedicationResponseDTO> getAll();
    void delete(Long id);
}
