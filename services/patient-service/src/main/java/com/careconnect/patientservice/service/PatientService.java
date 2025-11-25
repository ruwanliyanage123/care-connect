package com.careconnect.patientservice.service;

import com.careconnect.patientservice.dto.*;

import java.util.List;

public interface PatientService {
    PatientResponseDTO createPatient(PatientRequestDTO request);
    PatientResponseDTO getPatientById(Long id);
    PatientResponseDTO updatePatient(Long id, PatientUpdateDTO request);
    PatientResponseDTO partialUpdate(Long id, PatientUpdateDTO request);
    void deletePatient(Long id);
    PatientResponseDTO findByNic(String nic);
    PatientResponseDTO findByEmail(String email);
    PatientResponseDTO findByPhone(String phone);
    List<PatientResponseDTO> searchByName(String name);
    List<PatientResponseDTO> filterPatients(String city, String gender, String bloodGroup, int page, int size);
    PatientResponseDTO updateEmergencyContact(Long id, EmergencyContactDTO dto);
    PatientResponseDTO updateMedicalInfo(Long id, MedicalInfoDTO dto);
    List<PatientResponseDTO> getAllPatients();
    long countPatients();
}
