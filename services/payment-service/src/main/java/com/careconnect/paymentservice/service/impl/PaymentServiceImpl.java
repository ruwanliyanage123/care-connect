package com.careconnect.paymentservice.service.impl;

import com.careconnect.paymentservice.dto.InvoiceRequestDTO;
import com.careconnect.paymentservice.dto.InvoiceResponseDTO;
import com.careconnect.paymentservice.dto.PaymentRequestDTO;
import com.careconnect.paymentservice.dto.PaymentResponseDTO;
import com.careconnect.paymentservice.entity.Invoice;
import com.careconnect.paymentservice.entity.Payment;
import com.careconnect.paymentservice.enums.InvoiceStatus;
import com.careconnect.paymentservice.mapper.PaymentMapper;
import com.careconnect.paymentservice.repository.InvoiceRepository;
import com.careconnect.paymentservice.repository.PaymentRepository;
import com.careconnect.paymentservice.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper billingMapper;

    @Override
    public InvoiceResponseDTO createInvoice(InvoiceRequestDTO requestDTO) {
        Invoice invoice = billingMapper.toInvoiceEntity(requestDTO);
        invoice = invoiceRepository.save(invoice);
        return billingMapper.toInvoiceResponseDTO(invoice);
    }

    @Override
    @Transactional(readOnly = true)
    public InvoiceResponseDTO getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found: " + id));
        return billingMapper.toInvoiceResponseDTO(invoice);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceResponseDTO> getInvoicesByPatient(Long patientId) {
        return invoiceRepository.findByPatientId(patientId).stream()
                .map(billingMapper::toInvoiceResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponseDTO recordPayment(PaymentRequestDTO requestDTO) {
        Invoice invoice = invoiceRepository.findById(requestDTO.getInvoiceId())
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found: " + requestDTO.getInvoiceId()));
        Payment payment = billingMapper.toPaymentEntity(requestDTO, invoice);
        payment = paymentRepository.save(payment);
        BigDecimal totalPaid = paymentRepository.findByInvoice_Id(invoice.getId()).stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (totalPaid.compareTo(invoice.getTotalAmount()) >= 0) {
            invoice.setStatus(InvoiceStatus.PAID);
            invoiceRepository.save(invoice);
        }
        return billingMapper.toPaymentResponseDTO(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponseDTO> getPaymentsForInvoice(Long invoiceId) {
        return paymentRepository.findByInvoice_Id(invoiceId).stream()
                .map(billingMapper::toPaymentResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getRevenueBetween(LocalDate from, LocalDate to) {
        OffsetDateTime start = from.atStartOfDay().atOffset(ZoneOffset.UTC);
        OffsetDateTime end   = to.atTime(23, 59, 59).atOffset(ZoneOffset.UTC);
        return paymentRepository.sumRevenueBetween(start, end);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getTodayRevenue() {
        LocalDate today = LocalDate.now();
        return getRevenueBetween(today, today);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getMonthToDateRevenue() {
        LocalDate today = LocalDate.now();
        LocalDate firstOfMonth = today.withDayOfMonth(1);

        return getRevenueBetween(firstOfMonth, today);
    }
}
