package com.ecommerce.order_service.service;

import com.ecommerce.order_service.domain.Order;
import com.ecommerce.order_service.domain.OrderItem;
import com.ecommerce.order_service.domain.Product;
import com.ecommerce.order_service.dto.ItemProductDetailDTO;
import com.ecommerce.order_service.dto.response.OrderDetailDTO;
import com.ecommerce.order_service.dto.response.OrderInfoDTO;
import com.ecommerce.order_service.feign.OrderInterface;
import com.ecommerce.order_service.mapper.OrderMapper;
import com.ecommerce.order_service.repository.OrderRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repo;
    private final OrderMapper mapper;
    private final OrderInterface orderInterface;    // used to call to product-service

    public List<Order> findAllByUserId(Long userId) {
        return repo.findAllByUserId(userId);
    }

    public Order findOrderById(Long ordId) {
        Optional<Order> order = repo.findById(ordId);

        if (order.isPresent()) return order.get();
        else throw new NotFoundException();
    }

    public OrderInfoDTO findOrderInfoById(Long ordId) {
        // Order order = repo.findById(ordId).get();

        return mapper.getOrderInfoDTO(findOrderById(ordId));
    }

    // the most detailed info of an Order
    public OrderDetailDTO findOrderDetailsById(Long ordId) {
        Order order = findOrderById(ordId);
        List<ItemProductDetailDTO> itemProds = new ArrayList<>();

        if (order.getItems().size() > 0) {
            for (OrderItem item : order.getItems()) {
                // fetch each product from list items
                Product prod = orderInterface.getByProdId(item.getProdId()).getBody();
                itemProds.add(mapper.getItemProductDetailDTO(item, prod));
            }
        }

        if (itemProds.size() > 0) return mapper.getOrderDetailDTO(order, itemProds);
        else return null;
    }
}
