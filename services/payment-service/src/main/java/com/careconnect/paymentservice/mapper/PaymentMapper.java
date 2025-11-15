package com.careconnect.paymentservice.mapper;

import com.careconnect.paymentservice.dto.InvoiceItemDTO;
import com.careconnect.paymentservice.dto.InvoiceRequestDTO;
import com.careconnect.paymentservice.dto.InvoiceResponseDTO;
import com.careconnect.paymentservice.dto.PaymentRequestDTO;
import com.careconnect.paymentservice.dto.PaymentResponseDTO;
import com.careconnect.paymentservice.entity.Invoice;
import com.careconnect.paymentservice.entity.InvoiceItem;
import com.careconnect.paymentservice.entity.Payment;
import com.careconnect.paymentservice.enums.InvoiceStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMapper {

    public Invoice toInvoiceEntity(InvoiceRequestDTO dto) {
        if (dto == null) return null;

        Invoice invoice = Invoice.builder()
                .patientId(dto.getPatientId())
                .consultantId(dto.getConsultantId())
                .appointmentId(dto.getAppointmentId())
                .status(InvoiceStatus.PENDING)
                .totalAmount(BigDecimal.ZERO)
                .build();

        if (dto.getItems() != null) {
            dto.getItems().forEach(itemDTO -> {
                InvoiceItem item = toInvoiceItemEntity(itemDTO);
                invoice.addItem(item);
            });
        }

        // Calculate invoice total
        BigDecimal total = invoice.getItems().stream()
                .map(InvoiceItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        invoice.setTotalAmount(total);
        return invoice;
    }

    public InvoiceItem toInvoiceItemEntity(InvoiceItemDTO dto) {
        if (dto == null) return null;

        BigDecimal lineTotal = dto.getUnitPrice()
                .multiply(BigDecimal.valueOf(dto.getQuantity()));

        return InvoiceItem.builder()
                .description(dto.getDescription())
                .quantity(dto.getQuantity())
                .unitPrice(dto.getUnitPrice())
                .lineTotal(lineTotal)
                .build();
    }

    public InvoiceResponseDTO toInvoiceResponseDTO(Invoice invoice) {
        if (invoice == null) return null;

        List<InvoiceItemDTO> items = invoice.getItems().stream()
                .map(this::toInvoiceItemDTO)
                .collect(Collectors.toList());

        return InvoiceResponseDTO.builder()
                .id(invoice.getId())
                .patientId(invoice.getPatientId())
                .consultantId(invoice.getConsultantId())
                .appointmentId(invoice.getAppointmentId())
                .totalAmount(invoice.getTotalAmount())
                .status(invoice.getStatus())
                .createdAt(invoice.getCreatedAt())
                .updatedAt(invoice.getUpdatedAt())
                .items(items)
                .build();
    }

    public InvoiceItemDTO toInvoiceItemDTO(InvoiceItem entity) {
        if (entity == null) return null;

        return InvoiceItemDTO.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .quantity(entity.getQuantity())
                .unitPrice(entity.getUnitPrice())
                .lineTotal(entity.getLineTotal())
                .build();
    }

    public Payment toPaymentEntity(PaymentRequestDTO dto, Invoice invoice) {
        if (dto == null || invoice == null) return null;

        return Payment.builder()
                .invoice(invoice)
                .amount(dto.getAmount())
                .method(dto.getMethod())
                .reference(dto.getReference())
                .build();
    }

    public PaymentResponseDTO toPaymentResponseDTO(Payment payment) {
        if (payment == null) return null;

        return PaymentResponseDTO.builder()
                .id(payment.getId())
                .invoiceId(payment.getInvoice().getId())
                .amount(payment.getAmount())
                .method(payment.getMethod())
                .reference(payment.getReference())
                .paidAt(payment.getPaidAt())
                .build();
    }
}
