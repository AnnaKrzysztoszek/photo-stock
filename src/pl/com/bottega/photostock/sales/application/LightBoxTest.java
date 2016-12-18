package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.money.Money;

import java.util.Collection;

/**
 * Created by anna on 11.12.2016.
 */
public class LightBoxTest {

    public static void main(String[] args) {
        ProductRepository productRepository = new InMemoryProductRepository();
        Product product1 = productRepository.get("1");
        Product product2 = productRepository.get("2");
        Product product3 = productRepository.get("3");

        Client client = new Client("Johny X", new Address(), Money.valueOf(100));
        Client danny = new Client("Danny X", new Address(), Money.valueOf(100));

        LightBox lightBox1 = new LightBox(client, "cars");
        LightBox lightBox2 = new LightBox(client, "bmw");
        LightBox lightBox3 = new LightBox(danny, "fast cars");

        lightBox1.add(product1);
        lightBox1.add(product2);
        lightBox1.add(product3);

        lightBox2.add(product1);

        lightBox3.add(product3);

        product1.deactivate();

        //printLightBoxes(lightBox1, lightBox2, lightBox3);

        LightBox l = LightBox.joined(client, "Joined lightBox", lightBox1, lightBox2, lightBox3);
        System.out.println("Joined lightBox");
        printLightBox(l);
    }

    public static void printLightBoxes(Collection<LightBox> lightBoxes) {
        //System.out.println(lightBoxes.getClass() == LightBox[].class);
        int nr = 1;
        for (LightBox lightBox : lightBoxes) {
            System.out.println(String.format("%d. %s - %s", nr, lightBox.getName(), lightBox.getOwner().getName()));
            printLightBox(lightBox);
            nr++;
        }
    }

    private static void printLightBox(LightBox lightBox) {
        for (Product product : lightBox) {
            System.out.println(
                    String.format("%s%s | %s",
                        (product.isActive() ? "" : "X "),
                        product.getNumber(),
                        product.calculatePrice(lightBox.getOwner())
                    ));
        }
    }
}
