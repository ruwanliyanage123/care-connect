package com.careconnect.paymentservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class InvoiceRequestDTO {

    @NotNull
    private Long patientId;

    private Long consultantId;

    private Long appointmentId;

    @NotEmpty
    private List<InvoiceItemDTO> items;
}
