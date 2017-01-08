package pl.com.bottega.photostock.sales.presentation;

import java.util.Scanner;

/**
 * Created by anna on 07.01.2017.
 */
public class MainScreen {

    private final Scanner scanner;
    private final SearchScreen searchScreen;
    private final ReservationScreen reservationScreen;
    private final OfferScreen offerScreen;

    public MainScreen(Scanner scanner, SearchScreen searchScreen, ReservationScreen reservationScreen, OfferScreen offerScreen) {
        this.scanner = scanner;
        this.searchScreen = searchScreen;
        this.reservationScreen = reservationScreen;
        this.offerScreen = offerScreen;
    }

    public void print() {
        while (true) {
            printMenu();
            String command = getCommand();
            executeCommand(command);
        }
    }

    private void executeCommand(String command) {
        switch (command) {
            case "1":
                searchScreen.print();
                break;
            case "2":
                reservationScreen.print();
                break;
            case "3":
                offerScreen.print();
                break;
            default:
                System.out.println("Sorry, I do not understand");
        }
    }

    private void printMenu() {
        System.out.println("1. Search product");
        System.out.println("2. Reserve product");
        System.out.println("3. Generate offer");
    }

    private String getCommand() {
        System.out.println("What do you want to do?: ");
        return scanner.nextLine();
    }
}
