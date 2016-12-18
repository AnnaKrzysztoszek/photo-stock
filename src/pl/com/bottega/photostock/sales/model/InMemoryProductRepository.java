package pl.com.bottega.photostock.sales.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by anna on 17.12.2016.
 */
public class InMemoryProductRepository implements ProductRepository {

    private static final Map<String, Product> REPOSITORY = new HashMap<>();

    static {
        Collection<String> tags = Arrays.asList("przyroda", "motoryzacja");
        Product product1 = new Picture("1", "BMW", tags, RationalMoney.valueOf(3));
        Product product2 = new Picture("2", "Mercedes", tags, RationalMoney.valueOf(2));
        Product product3 = new Picture("3", "Porsche", tags, RationalMoney.valueOf(4));

        Product clip1 = new Clip("4", "Wściekłe pięści węża", 2l * 1000 *60 * 2, RationalMoney.valueOf(5));
        Product clip2 = new Clip("5", "Sum", 40l * 1000 *60 * 2, RationalMoney.valueOf(5));

        REPOSITORY.put("1", product1);
        REPOSITORY.put("2", product2);
        REPOSITORY.put("3", product3);

        REPOSITORY.put("4", clip1);
        REPOSITORY.put("5", clip2);
    }

    @Override
    public void put(Product product) {
        REPOSITORY.put(product.getNumber(), product);
    }


    @Override
    public Product get(String number) {
        return REPOSITORY.get(number);
    }
}