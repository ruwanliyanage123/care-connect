package com.careconnect.paymentservice.repository;

import com.careconnect.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByInvoice_Id(Long invoiceId);

    // ADD THIS:
    long countByPaidAtBetween(OffsetDateTime start, OffsetDateTime end);

    // ADD THIS FOR SUM:
    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.paidAt BETWEEN :start AND :end")
    BigDecimal sumRevenueBetween(@Param("start") OffsetDateTime start,
                                 @Param("end") OffsetDateTime end);
}
