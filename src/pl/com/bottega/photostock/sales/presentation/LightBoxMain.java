package pl.com.bottega.photostock.sales.presentation;

import pl.com.bottega.photostock.sales.application.AuthenticationProcess;
import pl.com.bottega.photostock.sales.application.ProductCatalog;
import pl.com.bottega.photostock.sales.application.PurchaseProcess;
import pl.com.bottega.photostock.sales.infrastructure.InMemoryClientRepository;
import pl.com.bottega.photostock.sales.infrastructure.InMemoryReservationRepository;
import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.money.Money;

import java.util.Scanner;

/**
 * Created by anna on 07.01.2017.
 */
public class LightBoxMain {

    private MainScreen mainScreen;
    private SearchScreen searchScreen;
    private ReservationScreen reservationScreen;
    private OfferScreen offerScreen;
    private LoginScreen loginScreen;

    public LightBoxMain() {
        Scanner scanner = new Scanner(System.in);
        ProductCatalog productCatalog = new ProductCatalog(new InMemoryProductRepository());
        ClientRepository clientRepository = new InMemoryClientRepository();
        AuthenticationProcess authenticationProcess = new AuthenticationProcess(clientRepository);
        ProductRepository productRepository = new InMemoryProductRepository();
        ReservationRepository reservationRepository = new InMemoryReservationRepository();
        PurchaseProcess purchaseProcess = new PurchaseProcess(clientRepository, reservationRepository, productRepository);
        loginScreen = new LoginScreen(scanner, authenticationProcess);
        searchScreen = new SearchScreen(scanner, productCatalog, loginScreen);
        reservationScreen = new ReservationScreen(scanner, loginScreen, purchaseProcess);
        offerScreen = new OfferScreen(scanner, loginScreen, purchaseProcess);
        mainScreen = new MainScreen(scanner, searchScreen, reservationScreen, offerScreen);
    }

    public void start() {
        loginScreen.print();
        mainScreen.print();
    }

    public static void main(String[] args) {
        new LightBoxMain().start();
    }
}
