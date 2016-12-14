package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by anna on 10.12.2016.
 */
public class Reservation {

    private Client client;
    private Collection<Picture> items;

    public Reservation(Client client) {
        this.client = client;
        this.items = new HashSet<Picture>();
    }

    public void add(Picture picture) {
        if (items.contains(picture))
            throw new IllegalArgumentException(String.format("Picture %s is already in your reservation", picture.getNumber()));
        if (!picture.isAvailable())
            throw new IllegalArgumentException(String.format("Picture %s is not available", picture.getNumber()));
        items.add(picture);
    }

    public void remove(Picture picture) {
        items.remove(picture);
    }

    public Offer generateOffer() {
        return new Offer(client, getActiveItems());
    }

    private Collection<Picture> getActiveItems() {
        Collection<Picture> activeItems = new HashSet<>();
        for (Picture picture : items)
            if (picture.isActive())
                activeItems.add(picture);
        return activeItems;
    }

    public int getItemsCount() {
        return items.size();
    }
}
