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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/invoices")
    public ResponseEntity<InvoiceResponseDTO> createInvoice(@Valid @RequestBody InvoiceRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.createInvoice(requestDTO));
    }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<InvoiceResponseDTO> getInvoice(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getInvoiceById(id));
    }

    @GetMapping("/invoices/patient/{patientId}")
    public ResponseEntity<List<InvoiceResponseDTO>> getInvoicesByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(paymentService.getInvoicesByPatient(patientId));
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> recordPayment(@Valid @RequestBody PaymentRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.recordPayment(requestDTO));
    }

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsForInvoice(@PathVariable Long invoiceId) {
        return ResponseEntity.ok(paymentService.getPaymentsForInvoice(invoiceId));
    }

    @GetMapping("/revenue")
    public ResponseEntity<BigDecimal> getRevenueBetween(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {
        return ResponseEntity.ok(paymentService.getRevenueBetween(from, to));
    }

    @GetMapping("/revenue/today")
    public ResponseEntity<BigDecimal> getTodayRevenue() {
        return ResponseEntity.ok(paymentService.getTodayRevenue());
    }

    @GetMapping("/revenue/month")
    public ResponseEntity<BigDecimal> getMonthToDateRevenue() {
        return ResponseEntity.ok(paymentService.getMonthToDateRevenue());
    }
}

