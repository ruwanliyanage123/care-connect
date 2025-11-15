package com.careconnect.consultantservice.service;

import com.careconnect.consultantservice.dto.EmergencyContactDTO;
import com.careconnect.consultantservice.dto.MedicalInfoDTO;
import com.careconnect.consultantservice.dto.PatientRequestDTO;
import com.careconnect.consultantservice.dto.PatientResponseDTO;
import com.careconnect.consultantservice.dto.PatientUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    Page<PatientResponseDTO> filterPatients(String city, String gender, String bloodGroup, Pageable pageable);
    PatientResponseDTO updateEmergencyContact(Long id, EmergencyContactDTO dto);
    PatientResponseDTO updateMedicalInfo(Long id, MedicalInfoDTO dto);
    List<PatientResponseDTO> getAllPatients();
    long countPatients();
}
