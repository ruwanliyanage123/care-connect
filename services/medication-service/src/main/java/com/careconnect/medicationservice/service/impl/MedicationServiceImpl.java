package com.careconnect.medicationservice.service.impl;

import com.careconnect.medicationservice.dto.*;
import com.careconnect.medicationservice.entity.Medication;
import com.careconnect.medicationservice.mapper.MedicationMapper;
import com.careconnect.medicationservice.repository.MedicationRepository;
import com.careconnect.medicationservice.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository repository;
    private final MedicationMapper mapper;

    @Override
    public MedicationResponseDTO create(MedicationRequestDTO dto) {
        Medication medication = mapper.toEntity(dto);
        return mapper.toResponseDTO(repository.save(medication));
    }

    @Override
    public MedicationResponseDTO update(Long id, MedicationUpdateDTO dto) {
        Medication medication = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medication not found"));

        mapper.applyUpdate(dto, medication);
        return mapper.toResponseDTO(repository.save(medication));
    }

    @Override
    public MedicationResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Medication not found"));
    }

    @Override
    public List<MedicationResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
