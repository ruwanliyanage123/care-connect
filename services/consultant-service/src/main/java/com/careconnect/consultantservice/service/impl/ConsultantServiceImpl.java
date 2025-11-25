package com.careconnect.consultantservice.service.impl;

import com.careconnect.consultantservice.service.ConsultantService;
import com.careconnect.consultantservice.dto.ConsultantRequestDTO;
import com.careconnect.consultantservice.dto.ConsultantResponseDTO;
import com.careconnect.consultantservice.dto.ConsultantUpdateDTO;
import com.careconnect.consultantservice.entity.Consultant;
import com.careconnect.consultantservice.exception.ConsultantNotFoundException;
import com.careconnect.consultantservice.mapper.ConsultantMapper;
import com.careconnect.consultantservice.repository.ConsultantInMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultantServiceImpl implements ConsultantService {

    private final ConsultantInMemoryRepository repository;
    private final ConsultantMapper mapper;

    @Override
    public ConsultantResponseDTO createConsultant(ConsultantRequestDTO request) {
        Consultant consultant = mapper.toEntity(request);
        consultant.setCreatedAt(OffsetDateTime.now());
        consultant.setUpdatedAt(OffsetDateTime.now());
        consultant = repository.save(consultant);
        return mapper.toResponseDTO(consultant);
    }

    @Override
    public ConsultantResponseDTO getConsultantById(Long id) {
        return mapper.toResponseDTO(findOrThrow(id));
    }

    @Override
    public List<ConsultantResponseDTO> getAllConsultants() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    public ConsultantResponseDTO updateConsultant(Long id, ConsultantUpdateDTO update) {
        Consultant consultant = findOrThrow(id);
        mapper.applyUpdate(update, consultant);
        consultant.setUpdatedAt(OffsetDateTime.now());
        repository.save(consultant);
        return mapper.toResponseDTO(consultant);
    }

    @Override
    public void deleteConsultant(Long id) {
        findOrThrow(id);
        repository.delete(id);
    }

    @Override
    public List<ConsultantResponseDTO> searchByName(String name) {
        if (name == null || name.isBlank()) {
            return getAllConsultants();
        }
        return repository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name).stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<ConsultantResponseDTO> getBySpecialization(String specialization) {
        if (specialization == null || specialization.isBlank()) {
            return getAllConsultants();
        }
        return repository.findBySpecializationIgnoreCase(specialization).stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    public ConsultantResponseDTO activateConsultant(Long id) {
        Consultant consultant = findOrThrow(id);
        consultant.setActive(true);
        consultant.setUpdatedAt(OffsetDateTime.now());
        repository.save(consultant);
        return mapper.toResponseDTO(consultant);
    }

    @Override
    public ConsultantResponseDTO deactivateConsultant(Long id) {
        Consultant consultant = findOrThrow(id);
        consultant.setActive(false);
        consultant.setUpdatedAt(OffsetDateTime.now());
        repository.save(consultant);
        return mapper.toResponseDTO(consultant);
    }

    @Override
    public long getConsultantCount() {
        return repository.findAll().size();
    }

    private Consultant findOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ConsultantNotFoundException("Consultant not found with id " + id));
    }
}
