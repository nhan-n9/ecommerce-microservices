package com.ecmicro.ecommerce.service;

import com.ecmicro.ecommerce.domain.*;
import com.ecmicro.ecommerce.dto.request.*;
import com.ecmicro.ecommerce.dto.response.*;
import com.ecmicro.ecommerce.dto.*;
import com.ecmicro.ecommerce.exception.ProductPurchaseException;
import com.ecmicro.ecommerce.feign.CustomerInterface;
import com.ecmicro.ecommerce.feign.ProductInterface;
import com.ecmicro.ecommerce.mapper.OrderMapper;
import com.ecmicro.ecommerce.repository.OrderRepository;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;

    private final ItemService itemService;

    // from Feign
    private final ProductInterface productInterface;    // used to call to product-service
    private final CustomerInterface customerInterface;

    // from RestTemplate
    private final ProductClient productClient;
    private final PaymentClient paymentClient;

    private final KafkaOrderProducer orderProducer;

    public List<Order> findAllByUserId(Long userId) {
        return orderRepository.findAllByCustomerId(userId);
    }

    public Order findOrderById(Long ordId) {
        Optional<Order> order = orderRepository.findById(ordId);

        if (order.isPresent()) return order.get();
        else throw new NotFoundException();
    }

    public OrderInfoDTO findOrderInfoById(Long ordId) {
        // Order order = orderRepository.findById(ordId).get();

        return mapper.toOrderInfoDTO(findOrderById(ordId));
    }

    // the most detailed info of an Order
    public OrderDetailDTO findOrderDetailsById(Long ordId) {
        Order order = findOrderById(ordId);
        List<ItemProductDetailDTO> itemProds = new ArrayList<>();

        if (order.getItems().size() > 0) {
            for (OrderItem item : order.getItems()) {
                // fetch each product from list items
                Product prod = productInterface.getByProdId(item.getProdId()).getBody();
                itemProds.add(mapper.toItemProductDetailDTO(item, prod));
            }
        }

        if (itemProds.size() > 0) return mapper.toOrderDetailDTO(order, itemProds);
        else return null;
    }

    public Long createOrder(OrderCreateDTO orderRequest) {
        if (orderRequest.getCreatedDate() == null) {
            orderRequest.setCreatedDate(LocalDateTime.now());
        }

        // 1. check if customer exists - via user-service
    // use "var" -> easier handling returned obj type
        var customer = customerInterface.findCustomerById(orderRequest.getCustomerId())
                .orElseThrow();     // handle exc

        log.info("Order Request: " + orderRequest);

        // 2. purchase products - via product-service
        List<ProductPurchaseResponseDTO> purchasedProds = productClient.purchaseProducts(orderRequest.getItems());
        for (ProductPurchaseResponseDTO dto : purchasedProds) {
            if (dto.getId() == null) {
                throw new ProductPurchaseException("ProductPurchaseDTO contains null id");
            }
        }

        // 3. create new Order (without items) -> get new order ID
        Order order = orderRepository.save(mapper.toOrder(orderRequest));

        // 4. create each OrderItem
        for (ItemCreateDTO item: orderRequest.getItems()) {
            ItemRequestDTO itemRequest = new ItemRequestDTO(
                    null,
                    order.getId(),
                    item
                );

            itemService.createProductItem(itemRequest);
        }

        // 5. handle payment - via payment-service (optional)
        Long paymentId = paymentClient.createPayment(
                new PaymentCreateDTO(
                        order.getTotalPrice(),
                        orderRequest.getPaymentMethod(),
                        order.getId(),
                        customer       // "UserInfoResponseDTO" obj
                )
        );

        // 6. send order confirmation to Kafka
        orderProducer.sendOrderConfirmation(
                new KafkaOrderConfirmation(
                        orderRequest.getCreatedDate(),
                        orderRequest.getTotalPrice(),
                        orderRequest.getPaymentMethod(),
                        customer,
                        purchasedProds
                )
        );

        // return new order's ID
        return order.getId();
    }

    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    public List<OrderInfoDTO> findAllOrderInfo() {
        List<OrderInfoDTO> orders = orderRepository.findAll()
                .stream()
                .map(mapper::toOrderInfoDTO)
                .collect(Collectors.toList());

        return orders;
    }
}
