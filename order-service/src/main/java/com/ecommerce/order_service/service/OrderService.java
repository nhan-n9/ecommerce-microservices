package com.ecommerce.order_service.service;

import com.ecommerce.order_service.domain.Order;
import com.ecommerce.order_service.dto.response.OrderInfoDTO;
import com.ecommerce.order_service.mapper.OrderMapper;
import com.ecommerce.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repo;
    private final OrderMapper mapper;

    public List<Order> findAllByUserId(Long userId) {
        return repo.findAllByUserId(userId);
    }

    public OrderInfoDTO findOrderById(Long ordId) {
        Order order = repo.findById(ordId).get();
        return mapper.getOrderInfoDTO(order);
    }

    public Order findOrderDetailsById(Long ordId) {
        return repo.findOrderWithItems(ordId);
    }
}
