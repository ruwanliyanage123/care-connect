package com.careconnect.reportservice.service.impl;

import com.careconnect.reportservice.client.AppointmentClient;
import com.careconnect.reportservice.client.ConsultantClient;
import com.careconnect.reportservice.client.PatientClient;
import com.careconnect.reportservice.client.PaymentClient;
import com.careconnect.reportservice.dto.AppointmentSummaryDTO;
import com.careconnect.reportservice.dto.DashboardSummaryDTO;
import com.careconnect.reportservice.dto.RevenueReportDTO;
import com.careconnect.reportservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final PatientClient patientClient;
    private final ConsultantClient consultantClient;
    private final AppointmentClient appointmentClient;
    private final PaymentClient paymentClient;

    @Override
    public Mono<DashboardSummaryDTO> getDashboardSummary() {

        return Mono.zip(
                patientClient.getPatientCount(),                // Mono<Long>
                consultantClient.getConsultantCount(),         // Mono<Long>
                appointmentClient.getAppointmentCount(),       // Mono<Long>
                paymentClient.getTodayRevenue().defaultIfEmpty(BigDecimal.ZERO),
                paymentClient.getMonthToDateRevenue().defaultIfEmpty(BigDecimal.ZERO)
        ).map(tuple -> DashboardSummaryDTO.builder()
                .totalPatients(tuple.getT1())
                .totalConsultants(tuple.getT2())
                .totalAppointments(tuple.getT3())
                .todayRevenue(tuple.getT4())
                .monthToDateRevenue(tuple.getT5())
                .build());
    }

    @Override
    public Mono<AppointmentSummaryDTO> getAppointmentSummary(LocalDate from, LocalDate to) {

        return appointmentClient.getAppointmentCountBetween(from, to)
                .defaultIfEmpty(0L)
                .map(total -> AppointmentSummaryDTO.builder()
                        .fromDate(from)
                        .toDate(to)
                        .totalAppointments(total)
                        .completedAppointments(0L)
                        .cancelledAppointments(0L)
                        .noShowAppointments(0L)
                        .build());
    }

    @Override
    public Mono<RevenueReportDTO> getRevenueReport(LocalDate from, LocalDate to) {

        return paymentClient.getRevenueBetween(from, to)
                .defaultIfEmpty(BigDecimal.ZERO)
                .map(total -> RevenueReportDTO.builder()
                        .fromDate(from)
                        .toDate(to)
                        .totalRevenue(total)
                        .totalInvoices(0L)
                        .paidInvoices(0L)
                        .pendingInvoices(0L)
                        .build());
    }
}