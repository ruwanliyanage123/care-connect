package com.careconnect.paymentservice.controller;

import com.careconnect.paymentservice.dto.InvoiceRequestDTO;
import com.careconnect.paymentservice.dto.InvoiceResponseDTO;
import com.careconnect.paymentservice.dto.PaymentRequestDTO;
import com.careconnect.paymentservice.dto.PaymentResponseDTO;
import com.careconnect.paymentservice.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/billing")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService billingService;

    @PostMapping("/invoices")
    public ResponseEntity<InvoiceResponseDTO> createInvoice(@Valid @RequestBody InvoiceRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(billingService.createInvoice(requestDTO));
    }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<InvoiceResponseDTO> getInvoice(@PathVariable Long id) {
        return ResponseEntity.ok(billingService.getInvoiceById(id));
    }

    @GetMapping("/invoices/patient/{patientId}")
    public ResponseEntity<List<InvoiceResponseDTO>> getInvoicesByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(billingService.getInvoicesByPatient(patientId));
    }

    @PostMapping("/payments")
    public ResponseEntity<PaymentResponseDTO> recordPayment(@Valid @RequestBody PaymentRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(billingService.recordPayment(requestDTO));
    }

    @GetMapping("/payments/invoice/{invoiceId}")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsForInvoice(@PathVariable Long invoiceId) {
        return ResponseEntity.ok(billingService.getPaymentsForInvoice(invoiceId));
    }
}
