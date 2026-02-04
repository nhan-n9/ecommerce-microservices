package com.ecmicro.ecommerce.feign;

import com.ecmicro.ecommerce.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// use as a Feign client to communicate with product-service

// tells Spring Cloud OpenFeign -> look up service named PRODUCT-SERVICE in Eureka registry
@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductInterface {

    @PostMapping("/api/v1/products/all/by-order")
    public ResponseEntity<List<Product>> getProdsByIds(@RequestBody List<Long> prodIds);

    @GetMapping("/api/v1/products/details/{id}")
    public ResponseEntity<Product> getByProdId(@PathVariable Long id);
}
