package com.careconnect.reportservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PatientClient {

    private final WebClient webClient;

    @Value("${careconnect.patient-service.base-url}")
    private String baseUrl;

    public Mono<Long> getPatientCount() {
        return webClient.get()
                .uri(baseUrl + "/count")
                .retrieve()
                .bodyToMono(Long.class)
                .defaultIfEmpty(0L);
    }
}
