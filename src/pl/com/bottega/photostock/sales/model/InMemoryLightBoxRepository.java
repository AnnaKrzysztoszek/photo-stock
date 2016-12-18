package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by anna on 14.12.2016.
 */
public class InMemoryLightBoxRepository implements LightBoxRepository {

    private static final Map<Client, Collection<LightBox>> REPOSITORY = new HashMap<>();

    @Override
    public void put(LightBox lightbox) {
        Client owner = lightbox.getOwner();
        REPOSITORY.putIfAbsent(owner, new HashSet<>());
        REPOSITORY.get(owner).add(lightbox);
    }

    @Override
    public Collection<LightBox> getFor(Client client) {
        return REPOSITORY.get(client);
    }
}
