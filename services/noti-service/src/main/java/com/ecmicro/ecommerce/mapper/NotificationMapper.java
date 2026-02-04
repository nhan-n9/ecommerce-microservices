package com.ecmicro.ecommerce.mapper;

import com.ecmicro.ecommerce.domain.Notification;
import com.ecmicro.ecommerce.domain.NotificationType;
import com.ecmicro.ecommerce.dto.KafkaOrderConfirmation;
import com.ecmicro.ecommerce.dto.KafkaPaymentNotification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationMapper {
    public Notification toNotification(KafkaPaymentNotification payment) {
        return Notification.builder()
                .type(NotificationType.PAYMENT_NOTIFICATION)
                .notificationDate(LocalDateTime.now())
                .paymentNotification(payment)
                .build();
    }

    public Notification toNotification(KafkaOrderConfirmation order) {
        return Notification.builder()
                .type(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(order)
                .build();
    }
}
