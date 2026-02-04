package com.ecmicro.ecommerce.repository;

import com.ecmicro.ecommerce.domain.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, Long> {
}
