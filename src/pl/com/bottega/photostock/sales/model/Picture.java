package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by anna on 10.12.2016.
 */
public class Picture extends AbstractProduct {

    private Collection<String> tags;

    public Picture(String number, String name, Collection<String> tags, Money catalogPrice, boolean active) {
        super(catalogPrice, active, name, number);//super wywołuje konstruktor klasy bazowej
        this.tags = new HashSet<String>(tags);
    }
    public Picture(String number, String name, Collection<String> tags, Money catalogPrice) {
        this(number, name, tags, catalogPrice, true);
    }

    @Override
    public Money calculatePrice(Client client) {
        return catalogPrice;
    }
}
