package com.careconnect.reportservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class PaymentClient {

    private final WebClient webClient;

    @Value("${careconnect.payment-service.base-url}")
    private String baseUrl;

    public Mono<BigDecimal> getTodayRevenue() {
        LocalDate today = LocalDate.now();

        return getRevenueBetween(today, today);
    }

    public Mono<BigDecimal> getMonthToDateRevenue() {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withDayOfMonth(1);

        return getRevenueBetween(firstDay, today);
    }

    public Mono<BigDecimal> getRevenueBetween(LocalDate from, LocalDate to) {
        return webClient.get()
                .uri(uri -> uri.path(baseUrl)
                        .queryParam("from", from.toString())
                        .queryParam("to", to.toString())
                        .build())
                .retrieve()
                .bodyToFlux(PaymentResponse.class)
                .map(PaymentResponse::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public record PaymentResponse(
            Long id,
            Long invoiceId,
            BigDecimal amount,
            String method,
            String reference,
            String paidAt
    ) {}
}
