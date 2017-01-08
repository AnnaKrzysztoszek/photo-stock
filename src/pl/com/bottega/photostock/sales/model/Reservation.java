package pl.com.bottega.photostock.sales.model;

import java.util.*;

/**
 * Created by anna on 10.12.2016.
 */
public class Reservation {

    private Client client;
    private Collection<Product> items;
    private String number;

    public Reservation(Client client) {
        this.client = client;
        this.number = UUID.randomUUID().toString();
        this.items = new LinkedList<>();
    }

    public void add(Product product) {
        if (items.contains(product))
            throw new IllegalArgumentException(String.format("Prroduct %s is already in your reservation", product.getNumber()));
        product.ensureAvailable();
        items.add(product);
        product.reservedPer(client);
    }

    public void remove(Product product) {
        if (!items.contains(product))
            throw new IllegalArgumentException(String.format("Product %s is not added to reservation.", product.getNumber()));
        items.remove(product);
        product.unreservedPer(client);
    }

    public Offer generateOffer() {
        Collection<Product> products = getActiveItems();
        if (products.isEmpty())
            throw new IllegalStateException("No active items in the reservation");
        return new Offer(client, products);
    }

    private Collection<Product> getActiveItems() {
        Collection<Product> activeItems = new HashSet<>();
        for (Product product : items)
            if (product.isActive())
                activeItems.add(product);
        return activeItems;
    }

    public int getItemsCount() {
        return items.size();
    }

    public String getNumber() {
        return number;
    }

    public boolean isOwnedBy(String clientNumber) {
        return client.getNumber().equals(clientNumber);
    }
}
