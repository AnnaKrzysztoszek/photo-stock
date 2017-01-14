package pl.com.bottega.photostock.sales.model;

import pl.com.bottega.photostock.sales.model.money.Money;

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
        return true;
    }

    public int getItemsCount() {
        return items.size();
    }

    public Money getTotalCost() {
        Money totalCost = Money.ZERO;
        for (Product product : items) {
            Money productCost = product.calculatePrice(client);
            totalCost = totalCost.add(productCost);
        }
        return totalCost;
    }

    private void sortProductsByPriceDesc() {
        //Collections.sort(this.items, new Comparator<Product>() {
        this.items.sort(new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                Money price1 = p1.calculatePrice(client);
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

    public List<Product> getItems() {
        return items;
    }
}
