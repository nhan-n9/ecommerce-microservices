package com.ecmicro.ecommerce.service;

import com.ecmicro.ecommerce.dto.KafkaOrderConfirmation;
import com.ecmicro.ecommerce.dto.KafkaPaymentNotification;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.ecmicro.ecommerce.domain.EmailTemplates.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(KafkaPaymentNotification paymentNoti)
            throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                                                message,
                                                MimeMessageHelper.MULTIPART_MODE_RELATED,
                                                StandardCharsets.UTF_8.name());

        // set sender email
        mimeMessageHelper.setFrom("test_ecom@email.com");
        mimeMessageHelper.setSubject(PAYMENT_NOTIFICATION.getSubject());

        final String templateName = PAYMENT_NOTIFICATION.getTemplate();

        String customerName = paymentNoti.getCusFirstName() + paymentNoti.getCusLastName();

        // prepare variables for Context
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("orderId", paymentNoti.getOrderId());
        variables.put("totalPrice", paymentNoti.getTotalPrice());

        Context context = new Context();
        context.setVariables(variables);

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);

            // set who to send email to
            mimeMessageHelper.setTo(paymentNoti.getCusEmail());

            // send email
            mailSender.send(message);

            log.info(String.format(
                    "INFO - Email successfully sent to %s - Template [%s]",
                    paymentNoti.getCusEmail(),
                    templateName));
        }
        catch (MessagingException e) {
            log.warn("WARNING - Cannot send email to [{}]", paymentNoti.getCusEmail());
        }
    }


    @Async
    public void sendOrderConfirmationEmail(KafkaOrderConfirmation orderConfirmation)
            throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                message,
                MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name());

        // set sender email
        mimeMessageHelper.setFrom("test_ecom@email.com");
        mimeMessageHelper.setSubject(ORDER_NOTIFICATION.getSubject());

        final String templateName = ORDER_NOTIFICATION.getTemplate();

        String customerEmail = orderConfirmation.getCustomer().getEmail();
        String customerName = orderConfirmation.getCustomer().getFirstName() + orderConfirmation.getCustomer().getLastName();

        // prepare variables for Context
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("paymentMethod", orderConfirmation.getPaymentMethod());
        variables.put("totalPrice", orderConfirmation.getTotalPrice());
        variables.put("products", orderConfirmation.getProducts());

        Context context = new Context();
        context.setVariables(variables);

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);

            // set who to send email to
            mimeMessageHelper.setTo(customerEmail);

            // send email
            mailSender.send(message);

            log.info(String.format(
                    "INFO - Email successfully sent to %s - Template [%s]",
                    customerEmail,
                    templateName));
        }
        catch (MessagingException e) {
            log.warn("WARNING - Cannot send email to [{}]", customerEmail);
        }
    }
}
