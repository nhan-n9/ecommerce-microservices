package com.ecmicro.ecommerce.controller;

import com.ecmicro.ecommerce.domain.Order;
import com.ecmicro.ecommerce.dto.OrderDTO;
import com.ecmicro.ecommerce.dto.request.OrderCreateDTO;
import com.ecmicro.ecommerce.dto.response.OrderDetailDTO;
import com.ecmicro.ecommerce.dto.response.OrderInfoDTO;
import com.ecmicro.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    ResponseEntity<Long> createOrder(@RequestBody OrderCreateDTO order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping
    ResponseEntity<List<OrderDTO>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/view-all/{userId}")
    ResponseEntity<List<Order>> getAllByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.findAllByUserId(userId);

        if (orders.size() > 0) return ResponseEntity.ok(orders);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<Order> findOrderById(@PathVariable Long id) {
        Order order = orderService.findOrderById(id);

        if (order != null) return ResponseEntity.ok(order);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/info/{id}")
    ResponseEntity<OrderInfoDTO> getOrderById(@PathVariable Long id) {
        OrderInfoDTO order = orderService.findOrderInfoById(id);

        if (order != null) return ResponseEntity.ok(order);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/details/{id}")
    ResponseEntity<OrderDetailDTO> getOrderDetailsById(@PathVariable Long id) {
        OrderDetailDTO order = orderService.findOrderDetailsById(id);

        if (order != null) return ResponseEntity.ok(order);
        return ResponseEntity.notFound().build();
    }
}
