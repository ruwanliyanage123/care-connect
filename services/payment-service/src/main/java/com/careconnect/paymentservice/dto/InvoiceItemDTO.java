package com.careconnect.paymentservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class InvoiceItemDTO {
    private Long id;
    @NotBlank
    private String description;
    @NotNull
    @Min(1)
    private Integer quantity;
    @NotNull
    private BigDecimal unitPrice;
    private BigDecimal lineTotal;
}
