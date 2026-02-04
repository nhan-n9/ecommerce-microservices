package com.ecmicro.ecommerce.service;

import com.ecmicro.ecommerce.dto.request.PaymentCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentClient {

    @Value("${application.config.payment-url}")
    private String paymentUrl;

    private final RestTemplate restTemplate;

    public Long createPayment(PaymentCreateDTO request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpEntity<PaymentCreateDTO> httpRequest = new HttpEntity<>(request, headers);

        ParameterizedTypeReference<Long> responseType = new ParameterizedTypeReference<Long>() {};

        ResponseEntity<Long> response = restTemplate.exchange(
                paymentUrl,
                HttpMethod.POST,
                httpRequest,
                responseType
        );

        if (response.getStatusCode().isError()) throw null;

        return response.getBody();
    }
}
