package pl.com.bottega.photostock.sales.presentation;

import pl.com.bottega.photostock.sales.application.AuthenticationProcess;
import pl.com.bottega.photostock.sales.model.Client;

import java.util.Scanner;

/**
 * Created by anna on 08.01.2017.
 */
public class LoginScreen {

    private Scanner scanner;
    private final AuthenticationProcess authenticationProcess;
    private Client client;

    public LoginScreen(Scanner scanner, AuthenticationProcess authenticationProcess) {
        this.scanner = scanner;
        this.authenticationProcess = authenticationProcess;
    }

    public void print() {
        while (true) {
            System.out.println("Enter client number: ");
            String clientNumber = scanner.nextLine();
            client = authenticationProcess.authenticate(clientNumber);
            if (client != null) {
                System.out.println(String.format("Welcome %s", client.getName()));
                return;
            }
            System.out.println("Incorrect client number. Try again.");
        }
    }

    public String getAuthenticatedClientNumber() {
        return client.getNumber();
    }

    public Client getClient() {
        return client;
    }
}
