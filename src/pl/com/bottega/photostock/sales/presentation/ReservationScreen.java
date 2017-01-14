package pl.com.bottega.photostock.sales.presentation;

import pl.com.bottega.photostock.sales.application.PurchaseProcess;
import pl.com.bottega.photostock.sales.model.product.ProductNotAvailableException;

import java.util.Scanner;

/**
 * Created by anna on 07.01.2017.
 */
public class ReservationScreen {

    private final Scanner scanner;
    private final LoginScreen loginScreen;
    private final PurchaseProcess purchaseProcess;

    public ReservationScreen(Scanner scanner, LoginScreen loginScreen, PurchaseProcess purchaseProcess) {
        this.scanner = scanner;
        this.loginScreen = loginScreen;
        this.purchaseProcess = purchaseProcess;
    }

    public void print() {
        while (true) {
            System.out.println("Enter product number to reservation: ");
            String productNumber = scanner.nextLine();
            try {
                String clientNumber = loginScreen.getAuthenticatedClientNumber();
                String reservationNumber = purchaseProcess.getReservation(clientNumber);
                purchaseProcess.add(reservationNumber, productNumber);
                System.out.println(String.format("Product %s was added to reservation %s", productNumber, reservationNumber));
                return;
            }
            catch (ProductNotAvailableException ex) {
                System.out.println(String.format("Sorry, product %s is not available", productNumber));
            }
            catch (IllegalArgumentException ex) {
                System.out.println("Incorrect product number.");
            }
        }
    }
}
