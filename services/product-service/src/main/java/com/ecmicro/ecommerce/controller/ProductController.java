package com.ecmicro.ecommerce.controller;

import com.ecmicro.ecommerce.dto.request.ProductPurchaseDTO;
import com.ecmicro.ecommerce.dto.response.ProductDetailDTO;
import com.ecmicro.ecommerce.dto.response.ProductPurchaseResDTO;
import com.ecmicro.ecommerce.service.ProductService;
import com.ecmicro.ecommerce.domain.Product;
import com.ecmicro.ecommerce.dto.request.ProductCreateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDetailDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Page<ProductDetailDTO> products = productService.findAllByPagination(page, size, sortBy, ascending);

        // check the list
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<ProductDetailDTO> getByProdId(@PathVariable Long id) {
        ProductDetailDTO product = productService.findById(id);

        if (product != null) return ResponseEntity.ok(product);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all/by-seller/{sellerId}")
    public ResponseEntity<List<ProductDetailDTO>> getBySellerId(@PathVariable Long sellerId) {
        List<ProductDetailDTO> products = productService.findBySellerId(sellerId);

        if (products.size() > 0) return ResponseEntity.ok(products);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/all/by-order")
    public ResponseEntity<List<ProductDetailDTO>> getProdsByIds(@RequestBody List<Long> prodIds) {
        List<ProductDetailDTO> products = productService.findByProdIds(prodIds);

        if (products.size() > 0) return ResponseEntity.ok(products);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createProduct(@RequestBody @Valid ProductCreateDTO requestDto) {
        Long newProdId = productService.createNewProduct(requestDto);

        if (newProdId != null) return ResponseEntity.ok(newProdId);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResDTO>> purchaseProducts(
                                        @RequestBody List<ProductPurchaseDTO> purchaseProds
    ) {
        List<ProductPurchaseResDTO> products = productService.purchaseProducts(purchaseProds);

        if (products.size() > 0) return ResponseEntity.ok(products);
        else return ResponseEntity.notFound().build();
    }
}
