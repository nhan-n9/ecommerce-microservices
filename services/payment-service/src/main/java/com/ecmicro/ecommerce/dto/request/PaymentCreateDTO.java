package com.ecmicro.ecommerce.dto.request;

import com.ecmicro.ecommerce.domain.PaymentMethod;
import com.ecmicro.ecommerce.dto.CustomerInfoDTO;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCreateDTO {
//  id?

    BigDecimal totalPrice;

    PaymentMethod paymentMethod;

    Long orderId;

    CustomerInfoDTO customer;
}
