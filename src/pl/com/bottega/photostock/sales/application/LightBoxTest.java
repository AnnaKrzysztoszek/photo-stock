package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.*;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by anna on 11.12.2016.
 */
public class LightBoxTest {

    public static void main(String[] args) {
        Collection<String> tags = Arrays.asList("przyroda", "motoryzacja");
        Picture picture1 = new Picture("BMW", tags, Money.valueOf(3));
        Picture picture2 = new Picture("Mercedes", tags, Money.valueOf(2));
        Picture picture3 = new Picture("Porsche", tags, Money.valueOf(4));

        Client client = new Client("Johny X", new Address(), Money.valueOf(100));
        Client danny = new Client("Danny X", new Address(), Money.valueOf(100));

        LightBox lightBox1 = new LightBox(client, "cars");
        LightBox lightBox2 = new LightBox(client, "bmw");
        LightBox lightBox3 = new LightBox(danny, "fast cars");

        lightBox1.add(picture1);
        lightBox1.add(picture2);
        lightBox1.add(picture3);

        lightBox2.add(picture1);

        lightBox3.add(picture3);

        picture1.deactivate();

        printLightBoxes(lightBox1, lightBox2, lightBox3);

        LightBox l = LightBox.joined(client, "Joined lightBox", lightBox1, lightBox2, lightBox3);
        System.out.println("Joined lightBox");
        printLightBox(l);
    }

    public static void printLightBoxes(LightBox ... lightBoxes) {
        //System.out.println(lightBoxes.getClass() == LightBox[].class);
        int nr = 1;
        for (LightBox lightBox : lightBoxes) {
            System.out.println(String.format("%d. %s - %s", nr, lightBox.getName(), lightBox.getOwner().getName()));
            printLightBox(lightBox);
            nr++;
        }
    }

    private static void printLightBox(LightBox lightBox) {
        for (Picture picture : lightBox) {
            System.out.println(
                    String.format("%s%s | %s",
                        (picture.isActive() ? "" : "X "),
                        picture.getNumber(),
                        picture.calculatePrice(lightBox.getOwner())
                    ));
        }
    }
}
