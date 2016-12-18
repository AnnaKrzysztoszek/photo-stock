package pl.com.bottega.photostock.sales.model;

import java.util.*;

/**
 * Created by anna on 10.12.2016.
 */
public class Offer {

    private List<Product> items;
    private Client client;

    public Offer(Client client, Collection<Product> items) {
        this.client = client;
        this.items = new LinkedList<>(items);
        sortProductsByPriceDesc();
    }

    public boolean sameAs(Offer other, Money money) {
        return false;
    }

    public int getItemsCount() {
        return items.size();
    }

    public RationalMoney getTotalCost() {
        RationalMoney totalCost = RationalMoney.ZERO;
        for (Product product : items) {
            RationalMoney productCost = product.calculatePrice(client);
            totalCost = totalCost.add(productCost);
        }
        return totalCost;
    }

    private void sortProductsByPriceDesc() {
        //Collections.sort(this.items, new Comparator<Product>() {
        this.items.sort(new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                RationalMoney price1 = p1.calculatePrice(client);
                Money price2 = p2.calculatePrice(client);
                /*if (price1.equals(price2))
                    return 0;
                if (price1.lt(price2))
                    return -1;
                return 1;*/
                return price2.compareTo(price1);//sortowanie malejące, rosnące będzie price1.compareTo(price2)
            }
        });
    }
}
