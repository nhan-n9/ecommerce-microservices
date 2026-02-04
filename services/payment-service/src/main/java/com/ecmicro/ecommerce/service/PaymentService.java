package com.ecmicro.ecommerce.service;

import com.ecmicro.ecommerce.domain.Payment;
import com.ecmicro.ecommerce.dto.request.PaymentCreateDTO;
import com.ecmicro.ecommerce.mapper.PaymentMapper;
import com.ecmicro.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final KafkaNotificationProducer kafkaProducer;

    public Long createPayment(PaymentCreateDTO request) {
        Payment newPayment = repository.save(mapper.toPayment(request));

        // send noti to Notification service
        kafkaProducer.sendPaymentNotification(
                mapper.toKafkaPaymentNotification(
                        newPayment.getId(),
                        request
                        )
        );

        return newPayment.getId();
    }

    public List<Payment> findAll() {
        return repository.findAll();
    }
}
