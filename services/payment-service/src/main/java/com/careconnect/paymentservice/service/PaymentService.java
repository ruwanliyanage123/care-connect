package com.careconnect.paymentservice.service;

import com.careconnect.paymentservice.dto.InvoiceRequestDTO;
import com.careconnect.paymentservice.dto.InvoiceResponseDTO;
import com.careconnect.paymentservice.dto.PaymentRequestDTO;
import com.careconnect.paymentservice.dto.PaymentResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PaymentService {

    InvoiceResponseDTO createInvoice(InvoiceRequestDTO requestDTO);

    InvoiceResponseDTO getInvoiceById(Long id);

    List<InvoiceResponseDTO> getInvoicesByPatient(Long patientId);

    PaymentResponseDTO recordPayment(PaymentRequestDTO requestDTO);

    List<PaymentResponseDTO> getPaymentsForInvoice(Long invoiceId);

    BigDecimal getRevenueBetween(LocalDate from, LocalDate to);

    BigDecimal getTodayRevenue();

    BigDecimal getMonthToDateRevenue();
}
