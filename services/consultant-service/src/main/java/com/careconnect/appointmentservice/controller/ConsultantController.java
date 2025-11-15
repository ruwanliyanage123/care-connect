package com.careconnect.appointmentservice.controller;

import com.careconnect.appointmentservice.dto.ConsultantRequestDTO;
import com.careconnect.appointmentservice.dto.ConsultantResponseDTO;
import com.careconnect.appointmentservice.dto.ConsultantUpdateDTO;
import com.careconnect.appointmentservice.service.ConsultantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/consultants")
@RequiredArgsConstructor
@Validated
public class ConsultantController {
    private final ConsultantService consultantService;

    @PostMapping
    public ResponseEntity<ConsultantResponseDTO> createConsultant(@Valid @RequestBody ConsultantRequestDTO request) {
        ConsultantResponseDTO response = consultantService.createConsultant(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultantResponseDTO> getConsultant(@PathVariable Long id) {
        return ResponseEntity.ok(consultantService.getConsultantById(id));
    }

    @GetMapping
    public ResponseEntity<List<ConsultantResponseDTO>> listConsultants(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specialization) {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(consultantService.searchByName(name));
        }
        if (specialization != null && !specialization.isBlank()) {
            return ResponseEntity.ok(consultantService.getBySpecialization(specialization));
        }
        return ResponseEntity.ok(consultantService.getAllConsultants());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultantResponseDTO> updateConsultant(
            @PathVariable Long id,
            @Valid @RequestBody ConsultantUpdateDTO update) {
        return ResponseEntity.ok(consultantService.updateConsultant(id, update));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<ConsultantResponseDTO> activateConsultant(@PathVariable Long id) {
        return ResponseEntity.ok(consultantService.activateConsultant(id));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ConsultantResponseDTO> deactivateConsultant(@PathVariable Long id) {
        return ResponseEntity.ok(consultantService.deactivateConsultant(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultant(@PathVariable Long id) {
        consultantService.deleteConsultant(id);
        return ResponseEntity.noContent().build();
    }
}
