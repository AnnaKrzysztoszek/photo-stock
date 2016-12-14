package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by anna on 14.12.2016.
 */
public class InMemoryLightBoxRepository implements LightBoxRepository {

    private static Map<Client, Collection<LightBox>> memory = new HashMap<>();

    @Override
    public void put(LightBox lightbox) {
        memory.put(lightbox.getOwner(), getFor(lightbox.getOwner()));
    }

    @Override
    public Collection<LightBox> getFor(Client client) {
        return memory.get(client);
    }
}
