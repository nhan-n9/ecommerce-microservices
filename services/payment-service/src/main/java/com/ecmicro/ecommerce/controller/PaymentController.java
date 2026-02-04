package com.ecmicro.ecommerce.controller;

import com.ecmicro.ecommerce.domain.Payment;
import com.ecmicro.ecommerce.dto.request.PaymentCreateDTO;
import com.ecmicro.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<Payment>> createPayment() {
        List<Payment> payments = paymentService.findAll();

        return ResponseEntity.ok(payments);
    }

    @PostMapping
    public ResponseEntity<Long> createPayment(@RequestBody PaymentCreateDTO request) {
        Long paymentId = paymentService.createPayment(request);

        if (paymentId != null) return ResponseEntity.ok(paymentId);
        return ResponseEntity.notFound().build();
    }
}
