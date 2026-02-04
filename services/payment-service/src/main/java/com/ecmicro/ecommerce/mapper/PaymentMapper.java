package com.ecmicro.ecommerce.mapper;

import com.ecmicro.ecommerce.domain.Payment;
import com.ecmicro.ecommerce.dto.KafkaPaymentNotification;
import com.ecmicro.ecommerce.dto.request.PaymentCreateDTO;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Payment toPayment(PaymentCreateDTO dto) {
        return Payment.builder()
                .id(null)
                .totalPrice(dto.getTotalPrice())
                .paymentMethod(dto.getPaymentMethod())
                .orderId(dto.getOrderId())
                .build();
    }

    public KafkaPaymentNotification toKafkaPaymentNotification(
            Long paymentId,
            PaymentCreateDTO request
    ) {
        return new KafkaPaymentNotification(
                paymentId,
                request.getOrderId(),
                request.getTotalPrice(),
                request.getPaymentMethod(),
                request.getCustomer().getFirstName(),
                request.getCustomer().getLastName(),
                request.getCustomer().getEmail()
        );
    }
}
