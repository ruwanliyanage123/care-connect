package com.careconnect.patientservice.service.impl;

import com.careconnect.patientservice.dto.EmergencyContactDTO;
import com.careconnect.patientservice.dto.MedicalInfoDTO;
import com.careconnect.patientservice.dto.PatientRequestDTO;
import com.careconnect.patientservice.dto.PatientResponseDTO;
import com.careconnect.patientservice.dto.PatientUpdateDTO;
import com.careconnect.patientservice.entity.Patient;
import com.careconnect.patientservice.exception.PatientNotFoundException;
import com.careconnect.patientservice.mapper.PatientMapper;
import com.careconnect.patientservice.repository.PatientRepository;
import com.careconnect.patientservice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO request) {
        Patient patient = patientMapper.toEntity(request);
        patient = patientRepository.save(patient);
        return patientMapper.toResponseDTO(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public PatientResponseDTO getPatientById(Long id) {
        Patient patient = findOrThrow(id);
        return patientMapper.toResponseDTO(patient);
    }

    @Override
    public PatientResponseDTO updatePatient(Long id, PatientUpdateDTO request) {
        Patient patient = findOrThrow(id);
        patientMapper.applyUpdate(request, patient);
        patient = patientRepository.save(patient);
        return patientMapper.toResponseDTO(patient);
    }

    @Override
    public PatientResponseDTO partialUpdate(Long id, PatientUpdateDTO request) {
        Patient patient = findOrThrow(id);
        patientMapper.applyUpdate(request, patient);
        patient = patientRepository.save(patient);
        return patientMapper.toResponseDTO(patient);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = findOrThrow(id);
        patientRepository.delete(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public PatientResponseDTO findByNic(String nic) {
        Patient patient = patientRepository.findByNationalId(nic)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found for NIC: " + nic));
        return patientMapper.toResponseDTO(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public PatientResponseDTO findByEmail(String email) {
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found for email: " + email));
        return patientMapper.toResponseDTO(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public PatientResponseDTO findByPhone(String phone) {
        Patient patient = patientRepository.findByPhone(phone)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found for phone: " + phone));
        return patientMapper.toResponseDTO(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientResponseDTO> searchByName(String name) {
        List<Patient> patients = patientRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
        return patients.stream()
                .map(patientMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PatientResponseDTO> filterPatients(String city, String gender, String bloodGroup, Pageable pageable) {
        Page<Patient> page = patientRepository.findAll(pageable);
        List<PatientResponseDTO> filtered = page.getContent().stream()
                .filter(p -> city == null || city.equalsIgnoreCase(p.getCity()))
                .filter(p -> gender == null || gender.equalsIgnoreCase(p.getGender()))
                .filter(p -> bloodGroup == null || bloodGroup.equalsIgnoreCase(p.getBloodGroup()))
                .map(patientMapper::toResponseDTO)
                .toList();
        return new PageImpl<>(filtered, pageable, page.getTotalElements());
    }

    @Override
    public PatientResponseDTO updateEmergencyContact(Long id, EmergencyContactDTO dto) {
        Patient patient = findOrThrow(id);
        patient.setEmergencyContactName(dto.getName());
        patient.setEmergencyContactPhone(dto.getPhone());
        patient = patientRepository.save(patient);
        return patientMapper.toResponseDTO(patient);
    }

    @Override
    public PatientResponseDTO updateMedicalInfo(Long id, MedicalInfoDTO dto) {
        Patient patient = findOrThrow(id);
        if (dto.getBloodGroup() != null) patient.setBloodGroup(dto.getBloodGroup());
        if (dto.getAllergies() != null) patient.setAllergies(dto.getAllergies());
        if (dto.getChronicDiseases() != null) patient.setChronicDiseases(dto.getChronicDiseases());
        patient = patientRepository.save(patient);
        return patientMapper.toResponseDTO(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientResponseDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public long countPatients() {
        return patientRepository.count();
    }

    private Patient findOrThrow(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found for id: " + id));
    }
}
