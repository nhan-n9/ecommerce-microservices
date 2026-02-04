package com.ecmicro.ecommerce.dto.response;

import com.ecmicro.ecommerce.dto.ItemProductDetailDTO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private Long id;

    private Long customerId;

    private LocalDateTime createdDate;

    private String status;

    private BigDecimal totalPrice;

    private List<ItemProductDetailDTO> items;
}
