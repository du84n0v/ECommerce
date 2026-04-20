package com.ecommerce.dto;

import com.ecommerce.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderDetailDto {
    private Integer orderId;
    private OrderStatus status;
    private Double totalAmount;
    private List<OrderItemDto> items;
}
