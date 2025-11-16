package com.careconnect.appointmentservice.service.impl;

import com.careconnect.appointmentservice.dto.ConsultantRequestDTO;
import com.careconnect.appointmentservice.dto.ConsultantResponseDTO;
import com.careconnect.appointmentservice.dto.ConsultantUpdateDTO;
import com.careconnect.appointmentservice.entity.Consultant;
import com.careconnect.appointmentservice.exception.ConsultantNotFoundException;
import com.careconnect.appointmentservice.mapper.ConsultantMapper;
import com.careconnect.appointmentservice.repository.ConsultantRepository;
import com.careconnect.appointmentservice.service.ConsultantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ConsultantServiceImpl implements ConsultantService {
    private final ConsultantRepository consultantRepository;
    private final ConsultantMapper consultantMapper;

    @Override
    public ConsultantResponseDTO createConsultant(ConsultantRequestDTO request) {
        Consultant consultant = consultantMapper.toEntity(request);
        consultant = consultantRepository.save(consultant);
        return consultantMapper.toResponseDTO(consultant);
    }

    @Override
    @Transactional(readOnly = true)
    public ConsultantResponseDTO getConsultantById(Long id) {
        Consultant consultant = consultantRepository.findById(id)
                .orElseThrow(() -> new ConsultantNotFoundException("Consultant not found with id " + id));
        return consultantMapper.toResponseDTO(consultant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConsultantResponseDTO> getAllConsultants() {
        return consultantRepository.findAll().stream()
                .map(consultantMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ConsultantResponseDTO updateConsultant(Long id, ConsultantUpdateDTO update) {
        Consultant consultant = consultantRepository.findById(id)
                .orElseThrow(() -> new ConsultantNotFoundException("Consultant not found with id " + id));
        consultantMapper.applyUpdate(update, consultant);
        consultant = consultantRepository.save(consultant);
        return consultantMapper.toResponseDTO(consultant);
    }

    @Override
    public void deleteConsultant(Long id) {
        Consultant consultant = consultantRepository.findById(id)
                .orElseThrow(() -> new ConsultantNotFoundException("Consultant not found with id " + id));
        consultantRepository.delete(consultant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConsultantResponseDTO> searchByName(String name) {
        if (name == null || name.isBlank()) return getAllConsultants();
        return consultantRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name).stream()
                .map(consultantMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConsultantResponseDTO> getBySpecialization(String specialization) {
        if (specialization == null || specialization.isBlank()) return getAllConsultants();
        return consultantRepository.findBySpecializationIgnoreCase(specialization).stream()
                .map(consultantMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ConsultantResponseDTO activateConsultant(Long id) {
        Consultant consultant = consultantRepository.findById(id)
                .orElseThrow(() -> new ConsultantNotFoundException("Consultant not found with id " + id));
        consultant.setActive(true);
        consultant = consultantRepository.save(consultant);
        return consultantMapper.toResponseDTO(consultant);
    }

    @Override
    public ConsultantResponseDTO deactivateConsultant(Long id) {
        Consultant consultant = consultantRepository.findById(id)
                .orElseThrow(() -> new ConsultantNotFoundException("Consultant not found with id " + id));
        consultant.setActive(false);
        consultant = consultantRepository.save(consultant);
        return consultantMapper.toResponseDTO(consultant);
    }

    @Override
    public long getConsultantCount() {
        return consultantRepository.count();
    }
}
