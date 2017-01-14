package pl.com.bottega.photostock.sales.infrastructure;

import pl.com.bottega.photostock.sales.model.Purchase;
import pl.com.bottega.photostock.sales.model.PurchaseRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anna on 14.01.2017.
 */
public class InMemoryPurchaseRepository implements PurchaseRepository {

    private static final Map<String, Purchase> REPOSITORY = new HashMap<>();

    @Override
    public void put(Purchase purchase) {
        REPOSITORY.put(purchase.getNumber(), purchase);
    }
}
