package com.ecmicro.ecommerce.service;

import com.ecmicro.ecommerce.dto.KafkaPaymentNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaNotificationProducer {
    private final KafkaTemplate<String, KafkaPaymentNotification> kafkaTemplate;

    public void sendPaymentNotification(KafkaPaymentNotification request) {
        log.info("Sending Payment notification to Notification service!");

        Message<KafkaPaymentNotification> message = MessageBuilder
                                        .withPayload(request)
                                        .setHeader(
                                                KafkaHeaders.TOPIC,
                                                "payment-topic"
                                        )
                                        .build();

        kafkaTemplate.send(message);

        // log the serialized message body
        log.info("Message sent from Payment service! With body: <{}>", request);
    }
}
