package com.ecmicro.ecommerce.dto;

import com.ecmicro.ecommerce.domain.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// data needed to send to Notification svc

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaOrderConfirmation {
    LocalDateTime createdDate;

    BigDecimal totalPrice;

    PaymentMethod paymentMethod;

    UserInfoResponseDTO customer;

    List<ProductPurchaseResponseDTO> products;
}
