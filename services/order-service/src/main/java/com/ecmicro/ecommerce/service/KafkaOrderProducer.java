package com.ecmicro.ecommerce.service;

import com.ecmicro.ecommerce.dto.KafkaOrderConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

// ###### Order service acts as Kafka producer -> send order events to Kafka topic ######

@Service
@RequiredArgsConstructor
@Slf4j      // Lombok annotation for logging
public class KafkaOrderProducer {
    private final KafkaTemplate<String, KafkaOrderConfirmation> kafkaTemplate;

    public void sendOrderConfirmation(KafkaOrderConfirmation orderConf) {
        log.info("Sending order confirmation to Notification service!");

        // Build the Kafka message - send to "order-topic"
        Message<KafkaOrderConfirmation> message = MessageBuilder
                                            .withPayload(orderConf)
                                            .setHeader(
                                                    KafkaHeaders.TOPIC,
                                                    "order-topic")  // same as in KafkaOrderTopicConfig
                                            .build();

        // Send message to Kafka topic, then will be sent to Kafka broker
        kafkaTemplate.send(message);
    }
}
