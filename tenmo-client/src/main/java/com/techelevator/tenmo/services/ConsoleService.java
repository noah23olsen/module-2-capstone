package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleService {



    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println("--------------------------------");

        System.out.println();
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    public void printWrongAmount() {
        System.out.println("Please enter a positive amount.");
        pause();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
    public void printUsers(User[] users){
        //TODO Exclude yourself from list
        System.out.println("----------------------------");
        System.out.println("Users");
        System.out.printf("%-10s %-10s\n", "ID", "Name");
        System.out.println("-----------------------------");
        for (User u : users){
            System.out.printf("%-10s %-10s\n", u.getId(),u.getUsername());
        }
        System.out.println("---------------------");
    }

    public void printTransfers(Transfer[] transfers, int userAccountId) {


        System.out.println("----------------------------");
        System.out.println("Transfers");
        System.out.printf("%-10s %-20s %-50s\n", "ID", "From/To", "Amount");
        System.out.println("-----------------------------");
        for (Transfer t : transfers) {


            String fromOrTo = "";
            if (t.getUserIdFrom() == userAccountId) {
                fromOrTo = t.getFromUsername();
            }
            else {fromOrTo = t.getToUsername();}
            System.out.printf("%-10s From: %-14s $%1.2f%n", t.getTransfer_id(), fromOrTo,t.getAmount());

        }

        System.out.println("---------------------");
        //TODO set if question so that FROM USER and TO USER can be plugged into the same print statement depending on which happened.
        //TODO Set FROM TO to have the "FROM:" or "TO:" inside the print formatting
        //TODO Begin step 6, dont burn down the house, kick ass.
        //TODO Write tests SOMEHOW???
    }

    public void printInsufficientFunds(){
        System.out.println("Insufficient Funds for this request.");
    }


}
