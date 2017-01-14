package pl.com.bottega.photostock.sales.presentation;

import pl.com.bottega.photostock.sales.infrastructure.InMemoryLightBoxRepository;
import pl.com.bottega.photostock.sales.infrastructure.InMemoryProductRepository;
import pl.com.bottega.photostock.sales.model.client.Address;
import pl.com.bottega.photostock.sales.model.client.Client;
import pl.com.bottega.photostock.sales.model.lightbox.LightBox;
import pl.com.bottega.photostock.sales.model.money.Money;
import pl.com.bottega.photostock.sales.model.product.Product;
import pl.com.bottega.photostock.sales.model.product.ProductRepository;

/**
 * Created by anna on 14.12.2016.
 */
public class LightBoxRepositoryTest {

    public static void main(String[] args) {
        Client client1 = new Client("Client 1", new Address(), Money.valueOf(100));
        Client client2 = new Client("Client 2", new Address(), Money.valueOf(100));

        InMemoryLightBoxRepository inMemoryLightBoxRepository = new InMemoryLightBoxRepository();

        ProductRepository productRepository = new InMemoryProductRepository();
        Product product1 = productRepository.get("1");
        Product product2= productRepository.get("2");
        Product product3 = productRepository.get("3");


        LightBox lightBox1 = new LightBox(client1, "lightBox1");
        LightBox lightBox2 = new LightBox(client1, "lightBox2");
        LightBox lightBox3 = new LightBox(client2, "lightBox3");
        LightBox lightBox4 = new LightBox(client2, "lightBox4");

        lightBox1.add(product1);
        lightBox2.add(product2);
        lightBox3.add(product3);

        inMemoryLightBoxRepository.put(lightBox1);
        inMemoryLightBoxRepository.put(lightBox2);
        inMemoryLightBoxRepository.put(lightBox3);
        inMemoryLightBoxRepository.put(lightBox4);

        LightBoxTest.printLightBoxes(inMemoryLightBoxRepository.getFor(client1));
        LightBoxTest.printLightBoxes(inMemoryLightBoxRepository.getFor(client2));
    }
}
