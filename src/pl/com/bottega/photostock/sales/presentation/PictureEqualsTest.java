package pl.com.bottega.photostock.sales.presentation;

import pl.com.bottega.photostock.sales.model.money.Money;
import pl.com.bottega.photostock.sales.model.product.Clip;
import pl.com.bottega.photostock.sales.model.product.Picture;
import pl.com.bottega.photostock.sales.model.product.Product;

import java.util.HashSet;

/**
 * Created by anna on 11.12.2016.
 */
public class PictureEqualsTest {

    public static void main(String[] args) {
        Product product1 = picture("123");
        Product product2 = picture(null);
        Product product3 = picture("123");
        Product product4 = picture("066");
        Product product5 = picture(null);

        Clip clip1 = clip("123");
        Clip clip2 = clip("225");
        Clip clip3 = clip("225");

        System.out.println("Positive:");
        System.out.println(product1.equals(product3));
        System.out.println(product3.equals(product1));
        System.out.println(product1.equals(product1));
        System.out.println(product2.equals(product5));
        System.out.println(clip2.equals(clip3));


        System.out.println("Negative:");
        System.out.println(product1.equals(13));
        System.out.println(product1.equals(null));
        System.out.println(product1.equals(clip2));
    }

    private static Clip clip(String number) {
        return new Clip(number, "", 500L, Money.valueOf(1));
    }

    private static Product picture(String number) {
        return new Picture(number, "", new HashSet<>(), Money.valueOf(100));
    }
}
