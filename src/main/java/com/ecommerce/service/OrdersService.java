package com.ecommerce.service;

import com.ecommerce.dto.OrderDetailDto;
import com.ecommerce.dto.OrderItemDto;
import com.ecommerce.entity.OrdersItem;
import com.ecommerce.entity.Orders;
import com.ecommerce.exceptions.NotFoundOrdersException;
import com.ecommerce.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersItemService ordersItemService;

    public List<Orders> getProfileOrders(Integer profileId) {
        return ordersRepository.getProfileOrders(profileId);
    }

    public OrderDetailDto getOrderDetailInformation(int orderId) {
        Orders order = ordersRepository.getOrderByOrderId(orderId);
        if(order == null){
            throw new NotFoundOrdersException("Order is not found");
        }
        List<OrdersItem> items = ordersItemService.getItemsByOrderId(orderId);

        return new OrderDetailDto(orderId, order.getStatus(), order.getTotalSumma(), parseOrderItemDto(items));
    }

    private List<OrderItemDto> parseOrderItemDto(List<OrdersItem> items) {
        List<OrderItemDto> result = new LinkedList<>();
        for(OrdersItem item :items){
            result.add(new OrderItemDto(item.getProduct().getName(), item.getQuantity(), item.getPrice()));
        }
        return result;
    }
}
