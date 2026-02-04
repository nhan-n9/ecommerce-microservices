package com.ecmicro.ecommerce.service;

import com.ecmicro.ecommerce.dto.request.ItemCreateDTO;
import com.ecmicro.ecommerce.dto.response.ProductPurchaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {
    @Value("${application.config.product-url}")
    private String productUrl;

    private final RestTemplate restTemplate;

    public List<ProductPurchaseResponseDTO> purchaseProducts(
            List<ItemCreateDTO> requestBody)
    {
        // create HTTP Header
        HttpHeaders headers = new HttpHeaders();
        // set key-value(s)/headers for the request
        headers.set(CONTENT_TYPE, "application/json");

        HttpEntity<List<ItemCreateDTO>> httpRequest = new HttpEntity<>(requestBody, headers);

        // define response type -> force RestTemplate to convert the response body into List<ProductPurchaseResponseDTO>
        ParameterizedTypeReference<List<ProductPurchaseResponseDTO>> responseType =
                new ParameterizedTypeReference<>() {};

        // after request is sent -> capture the response as ResponseEntity obj
        ResponseEntity<List<ProductPurchaseResponseDTO>> responseEntity =
                restTemplate.exchange(
                        productUrl + "/purchase",
                        HttpMethod.POST,
                        httpRequest,
                        responseType
                );

        if (responseEntity.getStatusCode().isError()) throw null;   // handle exc

        return responseEntity.getBody();
    }
}
