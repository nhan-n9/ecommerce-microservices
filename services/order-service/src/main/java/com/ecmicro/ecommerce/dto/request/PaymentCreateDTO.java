package com.ecmicro.ecommerce.dto.request;

import com.ecmicro.ecommerce.domain.PaymentMethod;
import com.ecmicro.ecommerce.dto.response.UserInfoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCreateDTO {

    BigDecimal totalPrice;

    PaymentMethod paymentMethod;

    Long orderId;

    UserInfoResponseDTO customer;
}
