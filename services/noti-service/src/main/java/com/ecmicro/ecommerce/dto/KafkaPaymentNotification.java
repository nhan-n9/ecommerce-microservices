package com.ecmicro.ecommerce.dto;

import com.ecmicro.ecommerce.domain.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaPaymentNotification {
    Long paymentId;

    Long orderId;

    BigDecimal totalPrice;

    PaymentMethod paymentMethod;

    String cusFirstName;

    String cusLastName;

    String cusEmail;
}
