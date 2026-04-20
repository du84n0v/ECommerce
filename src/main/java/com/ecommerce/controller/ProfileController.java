package com.ecommerce.controller;

import com.ecommerce.dto.OrderDetailDto;
import com.ecommerce.entity.Orders;
import com.ecommerce.entity.Profile;
import com.ecommerce.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    public List<Orders> getProfileOrders(Integer profileId) {
        return profileService.getProfileOrders(profileId);
    }

    public OrderDetailDto getOrderDetailInformation(int orderId) {
        return profileService.getOrderDetailInformation(orderId);
    }

    public boolean fillBalance(Integer profileId, double amount) {
        return profileService.fillBalance(profileId, amount);
    }

    public Profile getProfileById(Integer profileId) {
        return profileService.getProfileById(profileId);
    }
}
