package com.careconnect.paymentservice.dto;

import com.careconnect.paymentservice.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequestDTO {

    @NotNull
    private Long invoiceId;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private PaymentMethod method;

    private String reference;
}
