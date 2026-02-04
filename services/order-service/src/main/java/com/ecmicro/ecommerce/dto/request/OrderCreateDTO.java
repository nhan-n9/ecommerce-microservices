package com.ecmicro.ecommerce.dto.request;

import com.ecmicro.ecommerce.domain.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data   // auto-generates getters and setters, toString, equals, and hashCode methods
public class OrderCreateDTO {
    Long customerId;

    LocalDateTime createdDate;

    PaymentMethod paymentMethod;

    String status;

    BigDecimal totalPrice;

    List<ItemCreateDTO> items;
}
