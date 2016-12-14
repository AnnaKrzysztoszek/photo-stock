package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * Created by anna on 10.12.2016.
 */
public class LightBox implements Iterable<Picture>{

    private Client client;
    private String name;
    private Collection<Picture> items = new HashSet<Picture>();

    public LightBox(Client client, String name) {
        this.client = client;
        this.name = name;
    }

    public void add(Picture picture) {
        if (items.contains(picture))
            throw new IllegalArgumentException(String.format("LightBox already contains picture %s", picture.getNumber()));
        if (!picture.isAvailable())
            throw new IllegalArgumentException(String.format("Picture %s is not available", picture.getNumber()));
        items.add(picture);
    }

    public void remove(Picture picture) {
        if (!items.contains(picture))
            throw new IllegalArgumentException(String.format("LightBox does not contains picture %s", picture.getNumber()));
        items.remove(picture);
    }

    public void rename(String newName) {
        this.name = newName;
    }

    @Override
    public Iterator<Picture> iterator() {
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
            for (Picture picture : lightBox) {
                if (picture.isAvailable() && !items.contains(picture))
                    items.add(picture);
            }
        }
    }
}
