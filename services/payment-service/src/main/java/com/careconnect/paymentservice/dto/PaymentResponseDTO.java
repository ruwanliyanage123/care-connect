package com.careconnect.paymentservice.dto;

import com.careconnect.paymentservice.enums.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
public class PaymentResponseDTO {
    private Long id;
    private Long invoiceId;
    private BigDecimal amount;
    private PaymentMethod method;
    private String reference;
    private OffsetDateTime paidAt;
}
