package pl.com.bottega.photostock.sales.model;

import pl.com.bottega.photostock.sales.model.money.Money;

/**
 * Created by anna on 17.12.2016.
 */
public class VIPClient extends Client {

    private Money creditLimit;

    public VIPClient(String name, Address address, Money initialBalance, Money creditLimit) {
        super(name, address, ClientStatus.VIP, initialBalance);
        this.creditLimit = creditLimit;
    }

    @Override
    public boolean canAfford(Money money) {
        return balance.add(creditLimit).gte(money);
    }
}
