package com.careconnect.paymentservice.dto;

import com.careconnect.paymentservice.enums.InvoiceStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
public class InvoiceResponseDTO {
    private Long id;
    private Long patientId;
    private Long consultantId;
    private Long appointmentId;
    private BigDecimal totalAmount;
    private InvoiceStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private List<InvoiceItemDTO> items;
}
