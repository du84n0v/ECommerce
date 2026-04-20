package ui;

import controller.AuthController;
import dto.AuthDto;
import entity.Profile;

import java.util.Scanner;

public class AuthUI {

    private Scanner scannerNum;
    private Scanner scannerText;
    private AuthController controller;
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
