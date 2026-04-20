package com.ecommerce.ui;

import com.ecommerce.controller.AuthController;
import com.ecommerce.dto.AuthDto;
import com.ecommerce.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Scanner;

public class AuthUI {
    @Autowired
    @Qualifier("scannerNum")
    private Scanner scannerNum;
    @Autowired
    @Qualifier("scannerStr")
    private Scanner scannerText;
    @Autowired
    private AuthController controller;
    @Autowired
    private ProfileUI profileUI;


    public void run() {
        while(true){
            switch(menu()){
                case 1-> login();
                case 2-> register();
                case 0 -> {
                    return ;
                }
            }
        }
    }

    private void register() {
        System.out.print("Enter your name: ");
        String name = scannerText.next();
        System.out.print("Enter your phone number: ");
        String phone = scannerText.next();
        System.out.print("Enter your password: ");
        String password = scannerText.next();

        AuthDto dto = new AuthDto(name, phone, password);
        if(controller.register(dto)){
            System.out.println("Successfully registered!");
        }
        else{
            System.out.println("Phone number is already exist!");
        }

    }

    private void login() {
        System.out.print("Enter your phone number: ");
        String phone = scannerText.next();
        System.out.print("Enter your phone password: ");
        String password = scannerText.next();

        Profile profile = controller.getProfileByPhoneAndPassword(phone, password);
        if(profile == null){
            System.out.println("Login or Password is incorrect");
        }
        else {
            profileUI.start(profile);
        }
    }

    private int menu() {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║        🔐 AUTH MENU        ║");
        System.out.println("╠════════════════════════════╣");
        System.out.println("║ 1. 🔑 Login                ║");
        System.out.println("║ 2. 📝 Register             ║");
        System.out.println("║ 0. ❌ Exit                 ║");
        System.out.println("╚════════════════════════════╝");
        System.out.print(">>> ");

        return scannerNum.nextInt();
    }
}
