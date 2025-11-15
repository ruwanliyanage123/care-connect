package com.careconnect.reportservice.controller;

import com.careconnect.reportservice.dto.AppointmentSummaryDTO;
import com.careconnect.reportservice.dto.DashboardSummaryDTO;
import com.careconnect.reportservice.dto.RevenueReportDTO;
import com.careconnect.reportservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/dashboard")
    public Mono<DashboardSummaryDTO> getDashboardSummary() {
        return reportService.getDashboardSummary();
    }

    @GetMapping("/appointments")
    public Mono<AppointmentSummaryDTO> getAppointmentSummary(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return reportService.getAppointmentSummary(from, to);
    }

    @GetMapping("/revenue")
    public Mono<RevenueReportDTO> getRevenueReport(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return reportService.getRevenueReport(from, to);
    }
}
