package com.careconnect.appointmentservice.service;

import com.careconnect.appointmentservice.dto.ConsultantRequestDTO;
import com.careconnect.appointmentservice.dto.ConsultantResponseDTO;
import com.careconnect.appointmentservice.dto.ConsultantUpdateDTO;
import java.util.List;

public interface ConsultantService {
    ConsultantResponseDTO createConsultant(ConsultantRequestDTO request);
    ConsultantResponseDTO getConsultantById(Long id);
    List<ConsultantResponseDTO> getAllConsultants();
    ConsultantResponseDTO updateConsultant(Long id, ConsultantUpdateDTO update);
    void deleteConsultant(Long id);
    List<ConsultantResponseDTO> searchByName(String name);
    List<ConsultantResponseDTO> getBySpecialization(String specialization);
    ConsultantResponseDTO activateConsultant(Long id);
    ConsultantResponseDTO deactivateConsultant(Long id);
    long getConsultantCount();
}
