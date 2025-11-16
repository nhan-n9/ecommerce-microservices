package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.domain.Product;
import com.ecommerce.product_service.dto.ProductCreateDTO;
import com.ecommerce.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-svc")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Page<Product> products = productService.findAllByPagination(page, size, sortBy, ascending);

        // check the list
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Product> findByProdId(@PathVariable Long id) {
        Product product = productService.findById(id);

        if (product != null) return ResponseEntity.ok(product);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/products/by-seller/{sellerId}")
    public ResponseEntity<List<Product>> findBySellerId(@PathVariable Long sellerId) {
        List<Product> products = productService.findBySellerId(sellerId);

        if (products.size() > 0) return ResponseEntity.ok(products);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/product/create")
    public ResponseEntity<Long> createProduct(@RequestBody ProductCreateDTO requestDto) {
        Long newProdId = productService.createNewProduct(requestDto);

        if (newProdId != null) return ResponseEntity.ok(newProdId);
        return ResponseEntity.notFound().build();
    }}
