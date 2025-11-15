package com.careconnect.consultantservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "consultant_id", nullable = false)
    private Long consultantId;

    @Column(name = "appointment_datetime", nullable = false)
    private LocalDateTime appointmentDateTime;

    @Column(name = "status", nullable = false, length = 30)
    private String status;

    @Column(name = "reason", length = 500)
    private String reason;

    @Column(name = "notes", length = 1000)
    private String notes;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = this.createdAt;
        if (this.status == null) {
            this.status = "SCHEDULED";
        }
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}
