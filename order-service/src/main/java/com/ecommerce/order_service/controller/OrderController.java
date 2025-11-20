package com.ecommerce.order_service.controller;

import com.ecommerce.order_service.domain.Order;
import com.ecommerce.order_service.dto.response.OrderDetailDTO;
import com.ecommerce.order_service.dto.response.OrderInfoDTO;
import com.ecommerce.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/view-all/{userId}")
    ResponseEntity<List<Order>> getAllByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.findAllByUserId(userId);

        if (orders.size() > 0) return ResponseEntity.ok(orders);
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
