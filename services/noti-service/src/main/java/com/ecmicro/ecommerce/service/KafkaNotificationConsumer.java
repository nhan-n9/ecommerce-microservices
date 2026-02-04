package com.ecmicro.ecommerce.service;

// kafka consumer service to consume messages from topics (received from other microservices)

import com.ecmicro.ecommerce.dto.KafkaOrderConfirmation;
import com.ecmicro.ecommerce.dto.KafkaPaymentNotification;
import com.ecmicro.ecommerce.mapper.NotificationMapper;
import com.ecmicro.ecommerce.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

// steps: receive data -> save notification -> send email (use EmailService)

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaNotificationConsumer {
    private final NotificationRepository repository;
    private final NotificationMapper mapper;
    private final EmailService emailService;

    // 1. method to consume messages from "payment-topic"
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentNotification(KafkaPaymentNotification payment) throws MessagingException {
        log.info("Consuming message from [payment-topic]");

        // save the Notification obj
        repository.save(mapper.toNotification(payment));

        // send email
        emailService.sendPaymentSuccessEmail(payment);
    }

    // 2. method to consume messages from "order-topic"
    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmation(KafkaOrderConfirmation order) throws MessagingException {
        log.info("Consuming message from [order-topic]");

        // save the Notification obj
        repository.save(mapper.toNotification(order));

        // send email
        emailService.sendOrderConfirmationEmail(order);
    }
}
