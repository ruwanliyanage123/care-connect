package com.careconnect.reportservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DashboardSummaryDTO {
    private long totalPatients;
    private long totalConsultants;
    private long totalAppointments;
    private BigDecimal todayRevenue;
    private BigDecimal monthToDateRevenue;
}
