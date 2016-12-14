package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by anna on 10.12.2016.
 */
public class Offer {

    private Collection<Picture> items;
    private Client client;

    public Offer(Client client, Collection<Picture> items) {
        this.client = client;
        this.items = new HashSet<Picture>(items);
    }

    public boolean sameAs(Offer other, Money money) {
        return false;
    }

    public int getItemsCount() {
        return items.size();
    }

    public Money getTotalCost() {
        Money totalCost = Money.ZERO;
        for (Picture picture : items) {
            Money pictureCost = picture.calculatePrice(client);
            totalCost = totalCost.add(pictureCost);
        }
        return totalCost;
    }
}
