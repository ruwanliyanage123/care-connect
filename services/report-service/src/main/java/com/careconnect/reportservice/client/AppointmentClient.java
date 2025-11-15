package com.careconnect.reportservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AppointmentClient {

    private final WebClient webClient;

    @Value("${careconnect.appointment-service.base-url}")
    private String baseUrl;

    public Mono<Long> getAppointmentCount() {
        return webClient.get()
                .uri(baseUrl + "/count")
                .retrieve()
                .bodyToMono(Long.class);
    }

    public Mono<Long> getAppointmentCountBetween(LocalDate from, LocalDate to) {
        return webClient.get()
                .uri(uri -> uri
                        .path(baseUrl + "/count-range")
                        .queryParam("from", from)
                        .queryParam("to", to)
                        .build())
                .retrieve()
                .bodyToMono(Long.class)
                .defaultIfEmpty(0L);
    }
}
