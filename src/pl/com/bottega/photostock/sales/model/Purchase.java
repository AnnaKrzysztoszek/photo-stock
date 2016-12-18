package pl.com.bottega.photostock.sales.model;

import java.util.*;

/**
 * Created by anna on 10.12.2016.
 */
public class Purchase {

    private Client client;
    private Date purchaseDate;
    private List<Product> items;

    public Purchase(Client client, Collection<Product> items) {
        this.client = client;
        this.items = new LinkedList<>(items);
        sortProductsByNumber();
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
}
