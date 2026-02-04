package com.ecmicro.ecommerce.service;

import com.ecmicro.ecommerce.domain.Order;
import com.ecmicro.ecommerce.domain.Product;
import com.ecmicro.ecommerce.domain.OrderItem;
import com.ecmicro.ecommerce.dto.ItemProductDetailDTO;
import com.ecmicro.ecommerce.dto.response.OrderDetailDTO;
import com.ecmicro.ecommerce.dto.response.OrderInfoDTO;
import com.ecmicro.ecommerce.feign.OrderInterface;
import com.ecmicro.ecommerce.mapper.OrderMapper;
import com.ecmicro.ecommerce.repository.OrderRepository;
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
