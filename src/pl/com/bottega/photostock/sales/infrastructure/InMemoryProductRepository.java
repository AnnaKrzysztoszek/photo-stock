package pl.com.bottega.photostock.sales.infrastructure;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.ProductRepository;
import pl.com.bottega.photostock.sales.model.money.Money;

import java.util.List;

/**
 * Created by anna on 08.01.2017.
 */
public class InMemoryProductRepository implements ProductRepository {
    @Override
    public void put(Product product) {

    }

    @Override
    public Product get(String number) {
        return null;
    }

    @Override
    public List<Product> find(Client client, String nameQuery, String[] tags, Money priceFrom, Money priceTo, boolean onlyActive) {
        return null;
    }
}
