package com.ecmicro.ecommerce.domain;

import lombok.Getter;

// Enum to manage email templates and their subjects

public enum EmailTemplates {
    PAYMENT_NOTIFICATION("payment-notification.html", "Payment successfully processed!"),
    ORDER_NOTIFICATION("order-confirmation.html", "Order successfully processed!");

    @Getter
    private final String template;
    @Getter
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
