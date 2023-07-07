package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TenmoService;

import java.security.Principal;
import java.util.Arrays;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    public static final int EXIT_APP = 0;
    public static final int VIEW_CURRENT_BALANCE = 1;
    public static final int VIEW_PAST_TRANSFERS = 2;
    public static final int VIEW_PENDING_REQUESTS = 3;
    public static final int SEND_TE_BUCKS = 4;
    public static final int REQUEST_TE_BUCKS = 5;
    public static final int REGISTER_USER = 1;
    public static final int LOGIN_USER = 2;


    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);

    private AuthenticatedUser currentUser;
    TenmoService tenmoService;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            tenmoService = new TenmoService(API_BASE_URL, currentUser);
            mainMenu();
        }
    }

    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != EXIT_APP && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == REGISTER_USER) {
                handleRegister();
            } else if (menuSelection == LOGIN_USER) {
                handleLogin();
            } else if (menuSelection != EXIT_APP) {
                consoleService.printMessage("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        consoleService.printMessage("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            consoleService.printMessage("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != EXIT_APP) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == VIEW_CURRENT_BALANCE) {
                viewCurrentBalance();
            } else if (menuSelection == VIEW_PAST_TRANSFERS) {
                viewTransferHistory();
            } else if (menuSelection == VIEW_PENDING_REQUESTS) {
                viewPendingRequests();
            } else if (menuSelection == SEND_TE_BUCKS) {
                sendBucks();
            } else if (menuSelection == REQUEST_TE_BUCKS) {
                requestBucks();
            } else if (menuSelection == EXIT_APP) {
                continue;
            } else {
                consoleService.printMessage("Invalid Selection");
            }
            consoleService.pause();
        }
    }

    private void viewCurrentBalance() {
        Account account = tenmoService.getAccountFromUser();
        double teBucks = account.getTeBucks();
        System.out.printf("Your current account balance is: $%1.2f%n", teBucks);
    }

    private void viewTransferHistory() {
        Transfer[] transfers = tenmoService.listTransfers();
        consoleService.printTransfers(transfers);

        // TODO Auto-generated method stub
//        Transfer transfer = tenmoService.
    }

    private void viewPendingRequests() {
        // TODO Auto-generated method stub

    }

    private void sendBucks() {
        Account account = tenmoService.getAccountFromUser();
        User[] users = tenmoService.listUsers();
        consoleService.printUsers(users);

        int userIdTo = consoleService.promptForInt("Enter ID of user you are sending to (0 to cancel): ");
        if (userIdTo == 0) {
            mainMenu();
        }
        for (User u : users) {
            if (u.getId() == userIdTo) {
                break;
            } else {
                System.out.println("ID Invalid, Please Enter Valid ID");
                sendBucks();
            }
        }


        int amountToTransfer = consoleService.promptForInt("Enter amount:");
        if (amountToTransfer <= 0) {
            consoleService.printWrongAmount();
            sendBucks();
        }
        else if (amountToTransfer > account.getTeBucks()){
            consoleService.printInsufficientFunds();
            sendBucks();
        } else {
            tenmoService.transferBetweenAccounts(new Transfer(2,2,currentUser.getUser().getId(),userIdTo,amountToTransfer));
        }



    }
// TODO Auto-generated method stub


    private void requestBucks() {
        // TODO Auto-generated method stub

    }


}