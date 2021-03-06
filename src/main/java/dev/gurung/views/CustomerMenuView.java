package dev.gurung.views;

import dev.gurung.exceptions.ScannerInputException;
import dev.gurung.models.User;
import dev.gurung.services.InventoryService;
import dev.gurung.services.UserService;

import java.util.Scanner;

public class CustomerMenuView {

    private static UserService userService = new UserService();
    private static InventoryService inventoryService = new InventoryService();

    public static void display() {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            //display
            System.out.println("Customers menu, Choose a number:");
            System.out.println("1: view products");
            System.out.println("2: login to buy products and Edit Profile:");
            System.out.println("0: back");

            //inputs
            System.out.println("\nEnter your number:");

            String result = scanner.nextLine();
            try {
                if (result.matches(".*\\d.*")) {

                    //do something with inputs
                    switch (result){
                        case "1":
                            inventoryService.getAllInventories().forEach(System.out::println);
                            System.out.println("................................................................\n");

                            break;

                        case "2":
                            System.out.println("Please enter your email:");
                            String email = scanner.nextLine();

                            System.out.println("Please enter your password:");
                            String password = scanner.nextLine();

                            User user = userService.login(email, password);

                            if (user != null  && user.getRole().equals("Customer")) {

                                System.out.println("................................................................\n");
                                System.out.println("Welcome " + user.getFirstName().toUpperCase() + ",You are in Dashboard.");
                                System.out.println("your id : " +user.getId());
                                System.out.println("................................................................\n");

                                CustomerDashboard.display();

                            }
                            else {
                                System.out.println("Credentials do not match. ");
                            }
                            System.out.println("................................................................\n");

                            break;

                        case "0":
                            running = false;
                            break;
//
//                        default:
//                            System.out.println("Invalid Input");
                    }

                }
                throw new ScannerInputException("Invalid Input  '" + result + "', Enter Number from Choice!");
            }catch (ScannerInputException e){
                System.out.println(e.getMessage());
                System.out.println("................................................................\n");

            }

        }
    }
}
