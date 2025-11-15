package com.careconnect.reportservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class RevenueReportDTO {
    private LocalDate fromDate;
    private LocalDate toDate;
    private BigDecimal totalRevenue;
    private long totalInvoices;
    private long paidInvoices;
    private long pendingInvoices;
}
