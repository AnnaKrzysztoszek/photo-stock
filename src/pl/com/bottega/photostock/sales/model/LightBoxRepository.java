package pl.com.bottega.photostock.sales.model;

import java.util.Collection;

/**
 * Created by anna on 14.12.2016.
 */
public interface LightBoxRepository {

    void put(LightBox lightbox);

    Collection<LightBox> getFor(Client client);
}
