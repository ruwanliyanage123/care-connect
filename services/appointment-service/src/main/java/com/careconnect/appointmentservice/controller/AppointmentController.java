package com.careconnect.appointmentservice.controller;

import com.careconnect.appointmentservice.dto.AppointmentRequestDTO;
import com.careconnect.appointmentservice.dto.AppointmentResponseDTO;
import com.careconnect.appointmentservice.dto.AppointmentStatusUpdateDTO;
import com.careconnect.appointmentservice.dto.AppointmentUpdateDTO;
import com.careconnect.appointmentservice.service.AppointmentService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/health")
    public String health() {
        return "appointments-service OK";
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> create(
            @Valid @RequestBody AppointmentRequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appointmentService.create(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getById(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<Page<AppointmentResponseDTO>> getByPatient(
            @PathVariable Long patientId, Pageable pageable) {

        return ResponseEntity.ok(appointmentService.getByPatient(patientId, pageable));
    }

    @GetMapping("/consultant/{consultantId}")
    public ResponseEntity<Page<AppointmentResponseDTO>> getByConsultant(
            @PathVariable Long consultantId, Pageable pageable) {

        return ResponseEntity.ok(appointmentService.getByConsultant(consultantId, pageable));
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentResponseDTO>> getByStatus(
            @RequestParam(required = false) String status, Pageable pageable) {

        if (status == null || status.isBlank()) {
            status = "SCHEDULED";
        }
        return ResponseEntity.ok(appointmentService.getByStatus(status, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> update(
            @PathVariable Long id,
            @RequestBody AppointmentUpdateDTO updateDTO) {

        return ResponseEntity.ok(appointmentService.update(id, updateDTO));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<AppointmentResponseDTO> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody AppointmentStatusUpdateDTO statusDTO) {

        return ResponseEntity.ok(appointmentService.updateStatus(id, statusDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count-range")
    public ResponseEntity<Long> getAppointmentCountBetween(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        long count = appointmentService.countBetweenDates(from, to);
        return ResponseEntity.ok(count);
    }


    @GetMapping("/count")
    public ResponseEntity<Long> getTotalAppointmentCount() {

        LocalDate from = LocalDate.of(1970, 1, 1);
        LocalDate to = LocalDate.of(2100, 12, 31);

        long count = appointmentService.countBetweenDates(from, to);
        return ResponseEntity.ok(count);
    }

}
