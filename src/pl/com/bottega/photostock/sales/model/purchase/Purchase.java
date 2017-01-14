package pl.com.bottega.photostock.sales.model.purchase;

import pl.com.bottega.photostock.sales.model.client.Client;
import pl.com.bottega.photostock.sales.model.product.Product;

import java.util.*;

/**
 * Created by anna on 10.12.2016.
 */
public class Purchase {

    private Client client;
    private Date purchaseDate = new Date();//przed wywołaniem kostruktora zostanie utworzona data
    private List<Product> items;
    private String number;

    public Purchase(Client client, Collection<Product> items) {
        this.client = client;
        this.items = new LinkedList<>(items);
        this.number = UUID.randomUUID().toString();
        sortProductsByNumber();
        markProductsAsSold();
    }

    private void markProductsAsSold() {
        for (Product product : items) {
            product.soldPer(client);
        }
    }

    private void sortProductsByNumber() {
        this.items.sort(new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                String number1 = p1.getNumber();
                String number2 = p2.getNumber();
                return number1.compareTo(number2);
            }
        });
    }

    public Purchase(Client client, Product ... items) {//... dynamiczna liczba elementów
        this(client, Arrays.asList(items));
    }

    public int getItemsCount() {
        return 0;
    }

    public String getNumber() {
        return number;
    }
}
