package com.careconnect.paymentservice.repository;

import com.careconnect.paymentservice.entity.Invoice;
import com.careconnect.paymentservice.enums.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByPatientId(Long patientId);
    List<Invoice> findByStatus(InvoiceStatus status);
}
