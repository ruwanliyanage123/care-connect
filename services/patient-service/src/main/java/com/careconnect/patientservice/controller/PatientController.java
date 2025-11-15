package com.careconnect.consultantservice.controller;

import com.careconnect.consultantservice.dto.EmergencyContactDTO;
import com.careconnect.consultantservice.dto.MedicalInfoDTO;
import com.careconnect.consultantservice.dto.PatientRequestDTO;
import com.careconnect.consultantservice.dto.PatientResponseDTO;
import com.careconnect.consultantservice.dto.PatientUpdateDTO;
import com.careconnect.consultantservice.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO request) {
        return ResponseEntity.ok(patientService.createPatient(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientUpdateDTO request) {
        return ResponseEntity.ok(patientService.updatePatient(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> partialUpdate(@PathVariable Long id, @RequestBody PatientUpdateDTO request) {
        return ResponseEntity.ok(patientService.partialUpdate(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/emergency-contact")
    public ResponseEntity<PatientResponseDTO> updateEmergencyContact(@PathVariable Long id, @Valid @RequestBody EmergencyContactDTO dto) {
        return ResponseEntity.ok(patientService.updateEmergencyContact(id, dto));
    }

    @PatchMapping("/{id}/medical-info")
    public ResponseEntity<PatientResponseDTO> updateMedicalInfo(@PathVariable Long id, @RequestBody MedicalInfoDTO dto) {
        return ResponseEntity.ok(patientService.updateMedicalInfo(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countPatients() {
        return ResponseEntity.ok(patientService.countPatients());
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("patient-service is running");
    }

    @GetMapping("/by-nic")
    public ResponseEntity<PatientResponseDTO> searchByNIC(@RequestParam String nic) {
        return ResponseEntity.ok(patientService.findByNic(nic));
    }

    @GetMapping("/by-email")
    public ResponseEntity<PatientResponseDTO> searchByEmail(@RequestParam String email) {
        return ResponseEntity.ok(patientService.findByEmail(email));
    }

    @GetMapping("/by-phone")
    public ResponseEntity<PatientResponseDTO> searchByPhone(@RequestParam String phone) {
        return ResponseEntity.ok(patientService.findByPhone(phone));
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<PatientResponseDTO>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(patientService.searchByName(name));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<PatientResponseDTO>> filterPatients(@RequestParam(required = false) String city,
                                                                   @RequestParam(required = false) String gender,
                                                                   @RequestParam(required = false) String bloodGroup,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("lastName", "firstName").ascending());
        return ResponseEntity.ok(patientService.filterPatients(city, gender, bloodGroup, pageable));
    }
}
