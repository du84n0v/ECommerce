package com.ecommerce.service;

import com.ecommerce.entity.OrdersItem;
import com.ecommerce.repository.OrdersItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersItemService {
    @Autowired
    private OrdersItemRepository ordersItemRepository;

    public List<OrdersItem> getItemsByOrderId(int orderId) {
        return ordersItemRepository.getItemsByOrderId(orderId);
    }

    public void createOrderItem(int orderId, Integer productId, Integer quantity, Double price) {
        OrdersItem ordersItem = new OrdersItem();
        ordersItem.setOrderId(orderId);
        ordersItem.setProductId(productId);
        ordersItem.setQuantity(quantity);
        ordersItem.setPrice(price);
        ordersItemRepository.save(ordersItem);
    }
}
