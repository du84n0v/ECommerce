package com.ecommerce.ui;

import com.ecommerce.controller.ProfileController;
import com.ecommerce.entity.Orders;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private Map<Integer, Integer> cart = new HashMap<>();

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
                case 5 -> showAllProduct(profile);
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void showAllProduct(Profile profile) {
        List<Product> products = profileController.getAllProduct();
        if(products.isEmpty()){
            System.out.println("There is no product");
            return;
        }

        System.out.println("--------------------------------");
        System.out.println("            PRODUCTS            ");
        System.out.println("--------------------------------");
        for(Product product :products){
            System.out.printf("| %-12s : %-25d |\n", "Product ID", product.getId());
            System.out.printf("| %-12s : %-25s |\n", "Name", product.getName());
            System.out.printf("| %-12s : $%-24.2f |\n", "Price", product.getPrice());
            System.out.printf("| %-12s : %-25d |\n", "Quantity", product.getQuantity());
            System.out.println("--------------------------------\n");
        }

        options(profile);
    }

    private void options(Profile profile) {
        while(true){
            switch (optionsMenu()){
                case 1-> addCart(profile);
                case 2 -> showCart(profile);
//                case 3 -> createOrder(profile);
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void showCart(Profile profile) {
        if(cart.isEmpty()){
            System.out.println("There is nothing in cart");
            return ;
        }
        double totalSum = 0;
        List<Product> products = profileController.getProductsByIds(cart.keySet());
        for(Product product :products){
            System.out.printf("| %-12s : %-25d |\n", "Product ID", product.getId());
            System.out.printf("| %-12s : %-25s |\n", "Name", product.getName());
            System.out.printf("| %-12s : $%-24.2f |\n", "Price", product.getPrice());
            System.out.printf("| %-12s : %-25d |\n", "Quantity", cart.get(product.getId()));
            System.out.println("--------------------------------\n");
            totalSum += product.getPrice()*cart.get(product.getId());
        }

        System.out.println("Total Sum: " + totalSum);

        options2(profile);
    }

    private void options2(Profile profile) {
        while (true){
            switch (optionsMenu2()){
                case 1-> deleteOrderFromCart();
                case 2-> createOrder(profile);
                case 0-> {
                    return;
                }
            }
        }
    }

    private void createOrder(Profile profile) {
        if(cart.isEmpty()){
            System.out.println("There is nothing in cart. Please add some product to cart to order");
            return ;
        }
        try{
            profileController.createOrder(cart, profile.getId());
        }
        catch (Exception ee){
            System.out.println(ee.getMessage());
        }
    }

    private void deleteOrderFromCart() {
        System.out.print("Enter product ID: ");
        int productId = scannerNum.nextInt();
        if(cart.remove(productId) == null){
            System.out.println("Product not found");
        }
        else {
            System.out.println("Product removed from your cart");
        }
    }

    private void addCart(Profile profile) {
        System.out.print("Enter product ID: ");
        int productId = scannerNum.nextInt();
        System.out.print("Enter quantity: ");
        int quantity = scannerNum.nextInt();
        cart.merge(productId, quantity, Integer::sum);
    }

    private int optionsMenu() {
        System.out.println("==================================");
        System.out.println("1. Add cart");
        System.out.println("2. Show cart");
        System.out.println("3. Order");
        System.out.println("0. Back");
        System.out.print(">>>> ");

        return scannerNum.nextInt();
    }
    private int optionsMenu2() {
        System.out.println("==================================");
        System.out.println("1. Delete from cart");
        System.out.println("2. Order");
        System.out.println("0. Back");
        System.out.print(">>>> ");

        return scannerNum.nextInt();
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
        System.out.println("║ 5. Show all product        ║");
        System.out.println("║ 0. ❌ Exit                 ║");
        System.out.println("╚════════════════════════════╝");
        System.out.print(">>> ");

        return scannerNum.nextInt();
    }
}
