package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.ProductRepository;
import pl.com.bottega.photostock.sales.model.money.Money;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by anna on 07.01.2017.
 */
public class ProductCatalog {

    private ProductRepository productRepository;

    public ProductCatalog(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> find(Client client, String nameQuery, String[] tags, Money priceFrom, Money priceTo) {
        //aspekty techniczne: autentykacja, logowanie, tranzakcje, połączenia z bazą danych
        return productRepository.find(client, nameQuery, tags, priceFrom, priceTo, true);
    }
}
