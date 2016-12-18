package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Created by anna on 10.12.2016.
 */
public class LightBox implements Iterable<Product>{

    private Client client;
    private String name;
    private Collection<Product> items = new LinkedList<>();

    public LightBox(Client client, String name) {
        this.client = client;
        this.name = name;
    }

    public void add(Product product) {
        if (items.contains(product))
            throw new IllegalArgumentException(String.format("LightBox already contains product %s", product.getNumber()));
        product.ensureAvailable();
        items.add(product);
    }

    public void remove(Product product) {
        if (!items.contains(product))
            throw new IllegalArgumentException(String.format("LightBox does not contains product %s", product.getNumber()));
        items.remove(product);
    }

    public void rename(String newName) {
        this.name = newName;
    }

    @Override
    public Iterator<Product> iterator() {
        return items.iterator();
    }

    public String getName() {
        return name;
    }

    public Client getOwner() {
        return client;
    }

    public static LightBox joined(Client client, String name, LightBox ... lightBoxes) {
        LightBox newLightBox = new LightBox(client, name);
        /*for (LightBox lightBox : lightBoxes) {
            for (Picture picture : lightBox) {
                if (picture.isActive() && picture.isAvailable() && !newLightBox.items.contains(picture))
                    newLightBox.add(picture);
            }
        }*/
        newLightBox.join(lightBoxes);
        return newLightBox;
    }
    public void join(LightBox[] lightBoxes) {
        for (LightBox lightBox : lightBoxes) {
            for (Product product : lightBox) {
                if (product.isAvailable() && !items.contains(product))
                    items.add(product);
            }
        }
    }
}
