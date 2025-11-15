package com.careconnect.reportservice.service;

import com.careconnect.reportservice.dto.AppointmentSummaryDTO;
import com.careconnect.reportservice.dto.DashboardSummaryDTO;
import com.careconnect.reportservice.dto.RevenueReportDTO;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ReportService {

    Mono<DashboardSummaryDTO> getDashboardSummary();

    Mono<AppointmentSummaryDTO> getAppointmentSummary(LocalDate from, LocalDate to);

    Mono<RevenueReportDTO> getRevenueReport(LocalDate from, LocalDate to);
}
