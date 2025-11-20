package com.ecommerce.order_service.feign;

import com.ecommerce.order_service.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// use as a Feign client to communicate with product-service

@FeignClient(name = "PRODUCT-SERVICE")    // must be uppercase as shown on Eureka
public interface OrderInterface {

    @PostMapping("/product-svc/all/by-order")
    public ResponseEntity<List<Product>> getProdsByIds(@RequestBody List<Long> prodIds);

    @GetMapping("/product-svc/details/{id}")
    public ResponseEntity<Product> getByProdId(@PathVariable Long id);
}
