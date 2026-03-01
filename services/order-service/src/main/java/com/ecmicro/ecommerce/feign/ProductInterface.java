package com.ecmicro.ecommerce.feign;

import com.ecmicro.ecommerce.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// use as a Feign client to communicate with product-service

@FeignClient(name = "product-service", url = "${application.config.product-url}" // config in config-sv
)
public interface ProductInterface {

    @PostMapping("/all/by-order")
    public ResponseEntity<List<Product>> getProdsByIds(@RequestBody List<Long> prodIds);

    @GetMapping("/details/{id}")
    public ResponseEntity<Product> getByProdId(@PathVariable Long id);
}
