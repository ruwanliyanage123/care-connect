package com.careconnect.consultantservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "consultants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consultant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "gender")
    private String gender;
    @Column(name = "specialization", nullable = false)
    private String specialization;
    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;
    @Column(name = "registration_number", unique = true)
    private String registrationNumber;
    @Column(name = "languages")
    private String languages;
    @Column(name = "bio", length = 2000)
    private String bio;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "active", nullable = false)
    private Boolean active;
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        OffsetDateTime now = OffsetDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        if (this.active == null) this.active = true;
        if (this.rating == null) this.rating = 0.0;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}
