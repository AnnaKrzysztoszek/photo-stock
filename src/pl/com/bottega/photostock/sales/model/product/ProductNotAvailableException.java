package pl.com.bottega.photostock.sales.model.product;

import pl.com.bottega.photostock.sales.model.product.Product;

/**
 * Created by anna on 18.12.2016.
 */
public class ProductNotAvailableException extends RuntimeException{

    public ProductNotAvailableException(Product product) {
        super(String.format("Product %s is not available", product.getNumber()));
    }
}
