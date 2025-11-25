package com.careconnect.patientservice.service.impl;

import com.careconnect.patientservice.dto.*;
import com.careconnect.patientservice.entity.Patient;
import com.careconnect.patientservice.exception.PatientNotFoundException;
import com.careconnect.patientservice.mapper.PatientMapper;
import com.careconnect.patientservice.repository.PatientInMemoryRepository;
import com.careconnect.patientservice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientInMemoryRepository repo;
    private final PatientMapper mapper;

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO request) {
        Patient patient = mapper.toEntity(request);
        patient.setCreatedAt(OffsetDateTime.now());
        patient.setUpdatedAt(OffsetDateTime.now());

        patient = repo.save(patient);
        return mapper.toResponseDTO(patient);
    }

    @Override
    public PatientResponseDTO getPatientById(Long id) {
        return mapper.toResponseDTO(findOrThrow(id));
    }

    @Override
    public PatientResponseDTO updatePatient(Long id, PatientUpdateDTO request) {
        Patient patient = findOrThrow(id);
        mapper.applyUpdate(request, patient);
        patient.setUpdatedAt(OffsetDateTime.now());

        repo.save(patient);
        return mapper.toResponseDTO(patient);
    }

    @Override
    public PatientResponseDTO partialUpdate(Long id, PatientUpdateDTO request) {
        return updatePatient(id, request);
    }

    @Override
    public void deletePatient(Long id) {
        findOrThrow(id);
        repo.delete(id);
    }

    @Override
    public PatientResponseDTO findByNic(String nic) {
        return repo.findByNic(nic)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found for NIC: " + nic));
    }

    @Override
    public PatientResponseDTO findByEmail(String email) {
        return repo.findByEmail(email)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found for email: " + email));
    }

    @Override
    public PatientResponseDTO findByPhone(String phone) {
        return repo.findByPhone(phone)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found for phone: " + phone));
    }

    @Override
    public List<PatientResponseDTO> searchByName(String name) {
        return repo.searchByName(name).stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    /**
     * Custom pagination without Spring Data
     */
    @Override
    public List<PatientResponseDTO> filterPatients(String city, String gender, String bloodGroup, int page, int size) {

        List<Patient> filtered = repo.findAll().stream()
                .filter(p -> city == null || city.equalsIgnoreCase(p.getCity()))
                .filter(p -> gender == null || gender.equalsIgnoreCase(p.getGender()))
                .filter(p -> bloodGroup == null || bloodGroup.equalsIgnoreCase(p.getBloodGroup()))
                .toList();

        int start = page * size;
        int end = Math.min(start + size, filtered.size());

        if (start >= filtered.size()) return List.of();

        return filtered.subList(start, end).stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    public PatientResponseDTO updateEmergencyContact(Long id, EmergencyContactDTO dto) {
        Patient patient = findOrThrow(id);

        patient.setEmergencyContactName(dto.getName());
        patient.setEmergencyContactPhone(dto.getPhone());
        patient.setUpdatedAt(OffsetDateTime.now());

        repo.save(patient);
        return mapper.toResponseDTO(patient);
    }

    @Override
    public PatientResponseDTO updateMedicalInfo(Long id, MedicalInfoDTO dto) {
        Patient patient = findOrThrow(id);

        if (dto.getBloodGroup() != null) patient.setBloodGroup(dto.getBloodGroup());
        if (dto.getAllergies() != null) patient.setAllergies(dto.getAllergies());
        if (dto.getChronicDiseases() != null) patient.setChronicDiseases(dto.getChronicDiseases());

        patient.setUpdatedAt(OffsetDateTime.now());
        repo.save(patient);

        return mapper.toResponseDTO(patient);
    }

    @Override
    public List<PatientResponseDTO> getAllPatients() {
        return repo.findAll().stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    public long countPatients() {
        return repo.findAll().size();
    }

    private Patient findOrThrow(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found for id: " + id));
    }
}
