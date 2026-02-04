package com.ecmicro.ecommerce.domain;

import com.ecmicro.ecommerce.dto.KafkaOrderConfirmation;
import com.ecmicro.ecommerce.dto.KafkaPaymentNotification;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document     // this class is a MongoDB document
public class Notification {
    @Id
    private Long id;

    private NotificationType type;

    private LocalDateTime notificationDate;

    private KafkaOrderConfirmation orderConfirmation;

    private KafkaPaymentNotification paymentNotification;
}
