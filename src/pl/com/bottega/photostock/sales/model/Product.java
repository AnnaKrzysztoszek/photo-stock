package pl.com.bottega.photostock.sales.model;

/**
 * Created by anna on 17.12.2016.
 */
public interface Product {

    RationalMoney calculatePrice(Client client);

    boolean isAvailable();

    void reservedPer(Client client);

    void unreservedPer(Client client);

    boolean isReservedBy(Client client);

    void soldPer(Client client);

    String getNumber();

    boolean isActive();

    void deactivate();

    default void ensureAvailable() {
        if (!isAvailable())
            throw new ProductNotAvailableException(this);
    }
}
