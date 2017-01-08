package pl.com.bottega.photostock.sales.presentation;

import pl.com.bottega.photostock.sales.application.PurchaseProcess;
import pl.com.bottega.photostock.sales.model.Offer;
import pl.com.bottega.photostock.sales.model.Product;

import java.util.Scanner;

/**
 * Created by anna on 07.01.2017.
 */
public class OfferScreen {
    private final Scanner scanner;
    private final LoginScreen loginScreen;
    private final PurchaseProcess purchaseProcess;

    public OfferScreen(Scanner scanner, LoginScreen loginScreen, PurchaseProcess purchaseProcess) {
        this.scanner = scanner;
        this.loginScreen = loginScreen;
        this.purchaseProcess = purchaseProcess;
    }

    public void print() {
        String reservationNumber = purchaseProcess.getReservation(loginScreen.getAuthenticatedClientNumber());
        try {
            Offer offer = purchaseProcess.calculateOffer(reservationNumber);
            printOffer(offer);
        }
        catch (IllegalStateException ex) {
            System.out.println("No active products in the reservation. Add products.");
        }
    }

    private void printOffer(Offer offer) {
        System.out.println("Special offer for you: ");
        int i = 1;
        for (Product product : offer.getItems()) {
            System.out.println(String.format("%d. %s", i++, product.getName()));
        }
        System.out.println(String.format("Only: %s", offer.getTotalCost()));
    }
}
