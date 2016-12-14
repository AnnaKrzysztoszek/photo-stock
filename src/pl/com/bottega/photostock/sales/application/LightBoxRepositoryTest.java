package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.*;

import java.util.Arrays;

/**
 * Created by anna on 14.12.2016.
 */
public class LightBoxRepositoryTest {

    public static void main(String[] args) {
        Client client1 = new Client("Client 1", new Address(), Money.valueOf(100));
        Client client2 = new Client("Client 2", new Address(), Money.valueOf(100));

        InMemoryLightBoxRepository inMemoryLightBoxRepository = new InMemoryLightBoxRepository();

        Picture picture1 = new Picture("picture1", Arrays.asList("animals", "cats"), Money.valueOf(10));
        Picture picture2 = new Picture("picture2", Arrays.asList("Japan", "hanami"), Money.valueOf(20));
        Picture picture3 = new Picture("picture3", Arrays.asList("colours", "pink"), Money.valueOf(30));
        Picture picture4 = new Picture("picture4", Arrays.asList("books", "library"), Money.valueOf(40));

        LightBox lightBox1 = new LightBox(client1, "lightBox1");
        LightBox lightBox2 = new LightBox(client1, "lightBox2");
        LightBox lightBox3 = new LightBox(client2, "lightBox3");
        LightBox lightBox4 = new LightBox(client2, "lightBox4");

        lightBox1.add(picture1);
        lightBox2.add(picture2);
        lightBox3.add(picture3);
        lightBox4.add(picture4);

        inMemoryLightBoxRepository.put(lightBox1);
        inMemoryLightBoxRepository.put(lightBox2);
        inMemoryLightBoxRepository.put(lightBox3);
        inMemoryLightBoxRepository.put(lightBox4);

        inMemoryLightBoxRepository.getFor(client1);
        inMemoryLightBoxRepository.getFor(client2);

        LightBoxTest.printLightBoxes(lightBox1, lightBox2, lightBox3, lightBox4);
    }
}
