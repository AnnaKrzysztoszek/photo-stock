package pl.com.bottega.photostock.sales.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by anna on 10.12.2016.
 */
public class Purchase {

    private Client client;
    private Date purchaseDate;
    private Collection<Picture> items;

    public Purchase(Client client, Collection<Picture> items) {
        this.client = client;
        this.items = new HashSet<Picture>(items);
    }

    public Purchase(Client client, Picture ... items) {//... dynamiczna liczba elementów
        this(client, Arrays.asList(items));
    }

    public int getItemsCount() {
        return 0;
    }
}
