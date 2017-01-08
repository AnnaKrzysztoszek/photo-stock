package pl.com.bottega.photostock.sales.presentation;

import pl.com.bottega.photostock.sales.application.ProductCatalog;
import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.money.Money;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by anna on 07.01.2017.
 */
public class SearchScreen {
    private final Scanner scanner;
    private final ProductCatalog productCatalog;
    private final LoginScreen loginScreen;


    public SearchScreen(Scanner scanner, ProductCatalog productCatalog, LoginScreen loginScreen) {
        this.scanner = scanner;
        this.productCatalog = productCatalog;
        this.loginScreen = loginScreen;
    }

    public void print() {
        String name = getQuery();

        String[] tags = getTags();

        Money priceFrom = getMoney("Price From");

        Money priceTo = getMoney("Price To");

        List<Product> products = productCatalog.find(loginScreen.getClient(), name, tags, priceFrom, priceTo);

        printProducts(loginScreen.getClient(), products);
    }

    private void printProducts(Client client, List<Product> products) {
        System.out.println("Matching products: ");
        for (Product product : products) {
            System.out.println(String.format("%s | %s %s", product.getNumber(), product.getName(), product.calculatePrice(client)));
        }

        System.out.println("--------------------------------------------");
    }

    private Money getMoney(String prompt) {
        while (true) {
            try {
                System.out.println(prompt + ": ");
                float f = scanner.nextFloat();
                scanner.nextLine();
                return Money.valueOf(f);
            }catch (InputMismatchException ex) {
                scanner.nextLine();
                System.out.println("Write correct price, for example 9,99");
            }
        }
    }

    private String[] getTags() {
        System.out.println("Tags: ");
        String tagsRead = scanner.nextLine().trim();
        if (tagsRead.length() == 0)
            return null;
        else
            return tagsRead.split(" ");
    }

    private String getQuery() {
        System.out.println("Name: ");
        return scanner.nextLine();
    }
}