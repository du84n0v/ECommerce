package com.ecommerce.service;

import com.ecommerce.dto.OrderDetailDto;
import com.ecommerce.entity.Orders;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Profile;
import com.ecommerce.exceptions.InsufficientProductQuantityException;
import com.ecommerce.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private ProductService productService;

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

    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    public List<Product> getProductsByIds(Set<Integer> ids) {
        return productService.getProductsByIds(ids);
    }

    public void createOrder(Map<Integer, Integer> cart, Integer profileId) {
        List<Product> products = productService.getProductsByIds(cart.keySet());
        double totalSum = 0d;
        for(Product product :products){
            if(cart.get(product.getId()) > product.getQuantity()){
                throw new InsufficientProductQuantityException("There is not enough product quantity in stock");
            }
            totalSum += (cart.get(product.getId()) * product.getPrice());
        }
        ordersService.createOrder(products, cart, totalSum, profileId);
    }

    public void buyOrder(int orderId, Profile profile) {
        double totalSum = ordersService.buyOrder(orderId, profile);
        if(totalSum == -1){
            System.out.println("Hmm. Something went wrong");
            return ;
        }

        if(profileRepository.withdrawBalance(profile.getId(), totalSum) > 0){
            System.out.println("Successfully paid");
        }
        else {
            System.out.println("Hmm, Something went wrong");
        }
    }

    public void cancelOrder(int orderId, Profile profile) {
        double totalSum = ordersService.cancelOrder(orderId, profile);
        if(totalSum == 0){
            System.out.println("Successfully cancelled");
        }
        else if(totalSum == -1){
            System.out.println("Hmm, Something went wrong");
        }
        else {
            if(profileRepository.fillBalance(profile.getId(), totalSum)){
                System.out.println("Order successfully cancelled. Your money has been refunded");
            }
        }
    }
}
