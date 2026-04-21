package com.ecommerce.controller;

import com.ecommerce.dto.OrderDetailDto;
import com.ecommerce.entity.Orders;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Profile;
import com.ecommerce.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public List<Product> getAllProduct() {
        return profileService.getAllProduct();
    }

    public List<Product> getProductsByIds(Set<Integer> ids) {
        return profileService.getProductsByIds(ids);
    }

    public void createOrder(Map<Integer, Integer> cart, Integer profileId) {
        profileService.createOrder(cart, profileId);
    }

    public void buyOrder(int orderId, Profile profile) {
        profileService.buyOrder(orderId, profile);
    }
}
