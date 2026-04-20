package com.ecommerce.ui;

import com.ecommerce.controller.ProfileController;
import com.ecommerce.dto.OrderDetailDto;
import com.ecommerce.entity.Orders;
import com.ecommerce.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Scanner;

public class ProfileUI {

    @Autowired
    private ProfileController profileController;

    @Autowired
    @Qualifier("scannerNum")
    private Scanner scannerNum;
    @Autowired
    @Qualifier("scannerStr")
    private Scanner scannerText;

    public void start(Profile profile) {
        System.out.println("╔════════════════════════════╗");
        System.out.println("        WELCOME " + profile.getName());
        System.out.println("╠════════════════════════════╣");
        while(true){
            switch(menu()){
                case 1 -> showMyProfile(profile);
                case 2 -> showMyOrders(profile);
                case 3 -> showOrderDetails();
                case 4 -> fillBalance(profile);
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void fillBalance(Profile profile) {
        System.out.print("Enter amount: ");
        double amount = scannerNum.nextDouble();
        if(amount <= 0){
            System.out.println("Amount should be positive");
            return ;
        }
        if(profileController.fillBalance(profile.getId(), amount)){
            System.out.println("Successfully filled");
        }
        else {
            System.out.println("Hmmm, something went wrong...");
        }
    }

    private void showOrderDetails() {
        System.out.print("Enter order ID: ");
        int orderId = scannerNum.nextInt();
//        List<OrderDetailDto> orderDetail = profileController.getOrderDetailInformation(orderId);


    }

    private void showMyOrders(Profile profile) {
        List<Orders> myOrders = profileController.getProfileOrders(profile.getId());
        if(myOrders.isEmpty()){
            System.out.println("You don not have any order yet");
            return;
        }
        System.out.println("--------------------------------");
        System.out.println("            MY ORDERS           ");
        System.out.println("--------------------------------");

        for(Orders order :myOrders){
            System.out.printf("| %-12s : %-15d |\n", "Order ID", order.getId());
            System.out.printf("| %-12s : %-15s |\n", "Status", order.getStatus());
            System.out.printf("| %-12s : %-15.2f |\n", "Total Sum", order.getTotalSumma());
            System.out.println("--------------------------------\n");
        }
    }

    public void showMyProfile(Profile profile) {
        profile = profileController.getProfileById(profile.getId());
        System.out.println("--------------------------------");
        System.out.println("             PROFILE            ");
        System.out.println("--------------------------------");
        System.out.printf("| %-12s : %-15s |\n", "Name", profile.getName());
        System.out.printf("| %-12s : %-15s |\n", "Phone", profile.getPhone());
        System.out.printf("| %-12s : %-15s |\n", "Password", "********");
        System.out.printf("| %-12s : %-15.2f |\n", "Balance", profile.getBalance());
        System.out.println("--------------------------------");
    }

    private int menu() {
        System.out.println("╠════════════════════════════╣");
        System.out.println("║ 1. Show my profile         ║");
        System.out.println("║ 2. Show my orders          ║");
        System.out.println("║ 3. Show order detail       ║");
        System.out.println("║ 4. Fill balance            ║");
        System.out.println("║ 0. ❌ Exit                 ║");
        System.out.println("╚════════════════════════════╝");
        System.out.print(">>> ");

        return scannerNum.nextInt();
    }
}
