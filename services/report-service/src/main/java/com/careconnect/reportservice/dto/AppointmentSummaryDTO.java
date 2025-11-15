package com.careconnect.reportservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AppointmentSummaryDTO {
    private LocalDate fromDate;
    private LocalDate toDate;
    private long totalAppointments;
    private long completedAppointments;
    private long cancelledAppointments;
    private long noShowAppointments;
}
