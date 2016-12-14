package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by anna on 10.12.2016.
 */
public class Picture {

    private String number;
    private Collection<String> tags;
    private Money catalogPrice;
    private boolean active;
    private Client reservationOwner;
    private Client buyer;

    public Picture(String number, Collection<String> tags, Money catalogPrice, boolean active) {
        this.number = number;
        this.tags = new HashSet<String>(tags);
        this.catalogPrice = catalogPrice;
        this.active = active;
    }
    public Picture(String number, Collection<String> tags, Money catalogPrice) {
        this(number, tags, catalogPrice, true);
    }

    public Money calculatePrice(Client client) {
        return catalogPrice;
    }

    public boolean isAvailable() {
        return active && !isSold() && !isReserved();
    }

    private boolean isReserved() {
        return reservationOwner != null;
    }

    private boolean isSold() {
        return buyer != null;
    }

    public void reservedPer(Client client) {
        if (!isAvailable())
            throw new IllegalStateException(String.format("Picture %s is not available for reservation", getNumber()));
        reservationOwner = client;
    }

    public void unreservedPer(Client client) {
        ensureReservedBy(client);
        reservationOwner = null;
    }

    private void ensureReservedBy(Client client) {
        if (!isReservedBy(client))
            throw new IllegalArgumentException(String.format("Picture %s is not reserved by %s", getNumber(), client.getName()));
    }

    public boolean isReservedBy(Client client) {
        return isReserved() && client.equals(reservationOwner);
    }

    public void soldPer(Client client) {
        ensureReservedBy(client);
        buyer = client;
        unreservedPer(client);
    }

    public String getNumber() {
        return number;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || !(other instanceof Picture))
            return false;
        Picture otherPicture = (Picture) other;
        return (otherPicture.number == this.number) || (otherPicture.number != null) && otherPicture.number.equals(this.number);
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }

    public void deactivate() {
        active = false;
    }
}
