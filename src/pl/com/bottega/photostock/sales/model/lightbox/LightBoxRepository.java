package pl.com.bottega.photostock.sales.model.lightbox;

import pl.com.bottega.photostock.sales.model.client.Client;
import pl.com.bottega.photostock.sales.model.lightbox.LightBox;

import java.util.Collection;

/**
 * Created by anna on 14.12.2016.
 */
public interface LightBoxRepository {

    void put(LightBox lightbox);

    Collection<LightBox> getFor(Client client);

    LightBox findLightBox(Client client, String lightBoxName);

    Collection<String> getLightBoxNames(Client client);
}
