package com.ecmicro.ecommerce.dto;

import com.ecmicro.ecommerce.domain.PaymentMethod;
import com.ecmicro.ecommerce.dto.response.ProductPurchaseResponseDTO;
import com.ecmicro.ecommerce.dto.response.UserInfoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// data needed to send to Notification svc

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmationDTO {
    LocalDateTime createdDate;

    BigDecimal totalPrice;

    PaymentMethod paymentMethod;

    UserInfoResponseDTO customer;

    List<ProductPurchaseResponseDTO> products;
}
