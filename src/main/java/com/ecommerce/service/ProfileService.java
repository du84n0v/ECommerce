package com.ecommerce.service;

import com.ecommerce.dto.OrderDetailDto;
import com.ecommerce.entity.Orders;
import com.ecommerce.entity.Profile;
import com.ecommerce.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private OrdersService ordersService;

    public List<Orders> getProfileOrders(Integer profileId) {
        return ordersService.getProfileOrders(profileId);
    }

    public OrderDetailDto getOrderDetailInformation(int orderId) {
        return ordersService.getOrderDetailInformation(orderId);
    }

    public boolean fillBalance(Integer profileId, double amount) {
        return profileRepository.fillBalance(profileId, amount);
    }

    public Profile getProfileById(Integer profileId) {
        return profileRepository.getProfileById(profileId);
    }
}
