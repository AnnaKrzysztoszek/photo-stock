package pl.com.bottega.photostock.sales.model;

/**
 * Created by anna on 17.12.2016.
 */
public interface ProductRepository {

    void put(Product product);

    Product get(String number);
}
