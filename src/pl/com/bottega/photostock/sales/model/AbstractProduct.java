package pl.com.bottega.photostock.sales.model;

import pl.com.bottega.photostock.sales.model.money.Money;

/**
 * Created by anna on 17.12.2016.
 */
public abstract class AbstractProduct implements Product {

    protected String number;//ponieważ number jest w equalsie, to zostaje protected
    private String name;
    protected Money catalogPrice;
    private boolean active;
    private Client reservationOwner;
    private Client buyer;

    public AbstractProduct(Money catalogPrice, boolean active, String number, String name) {
        this.active = active;
        this.name = name;
        this.catalogPrice = catalogPrice;
        this.number = number;
    }

    public abstract Money calculatePrice(Client client);

    @Override
    public boolean isAvailable() {
        return active && !isSold() && !isReserved();
    }

    private boolean isReserved() {
        return reservationOwner != null;
    }

    private boolean isSold() {
        return buyer != null;
    }

    @Override
    public void reservedPer(Client client) {
        ensureAvailable();
        reservationOwner = client;
    }

    @Override
    public void unreservedPer(Client client) {
        ensureReservedBy(client);
        reservationOwner = null;
    }

    private void ensureReservedBy(Client client) {
        if (!isReservedBy(client))
            throw new IllegalArgumentException(String.format("Picture %s is not reserved by %s", getNumber(), client.getName()));
    }

    @Override
    public boolean isReservedBy(Client client) {
        return isReserved() && client.equals(reservationOwner);
    }

    @Override
    public void soldPer(Client client) {
        ensureReservedBy(client);
        buyer = client;
        unreservedPer(client);
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void deactivate() {
        active = false;
    }

    /*@Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || !(other instanceof Product))
            return false;
        AbstractProduct otherProduct = (AbstractProduct) other;
        return (otherProduct.number == this.number) || (otherProduct.number != null) && otherProduct.number.equals(this.number);
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractProduct product = (AbstractProduct) o;
        return number != null ? number.equals(product.number) : product.number == null;
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }
}
