package pl.com.bottega.photostock.sales.model.client;

import pl.com.bottega.photostock.sales.model.money.Money;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by anna on 10.12.2016.
 */
public class Client {

    private static int clientNumber;
    private String name;
    private Address address;
    private ClientStatus status;
    protected Money balance;//saldo
    private Collection<Transaction> transactions;
    private boolean active;
    private String number;

    public Client(String name, Address address, ClientStatus status, Money initialBalance) {
        this(nextNumber(), name, address, status, initialBalance, true, new LinkedList<>());

        if (!initialBalance.equals(Money.ZERO))
            this.transactions.add(new Transaction(initialBalance, "Opening account"));
    }

    public Client(String number, String name, Address address, ClientStatus status, Money initialBalance, boolean active, Collection<Transaction> transactions) {
        this.name = name;
        this.address = address;
        this.status = status;
        this.balance = initialBalance;
        this.transactions = new LinkedList<>(transactions);
        this.number = number;
        this.active = active;
    }

    public Client(String name, Address address, Money balance) {
        this(name, address, ClientStatus.STANDARD, balance);
    }


    private static String nextNumber() {
        clientNumber += 100;
        return String.valueOf(clientNumber);
    }

    public boolean canAfford(Money money) {
        return balance.gte(money);
    }

    public void charge(Money money, String reason) {
        if (money.lte(Money.ZERO))
            throw new IllegalArgumentException("Negative charge amount prohibited");
        if (canAfford(money)) {
            Transaction chargeTransaction = new Transaction(money.opposite(), reason);
            transactions.add(chargeTransaction);
            balance = balance.subtract(money);
        }else {
            String template = "Client balance is %s and requested amount was %s";
            String message = String.format(template, balance, money);
            throw new CantAffordException(message);
        }
    }

    public void recharge(Money money) {
        if (money.lte(Money.ZERO))
            throw new IllegalArgumentException("Negative charge amount prohibited");
        Transaction transaction = new Transaction(money, "Recharge account");
        transactions.add(transaction);
        balance = balance.add(money);
    }

    public String getName() {
        return name;
    }

    public String introduce() {
        /*String statusName = null;
        switch (status) {
            case STANDARD:
                statusName = "Standardowy";
                break;
            case VIP:
                statusName = "VIP";
                break;
            case GOLD:
                statusName = "Złoty";
                break;
            case SILVER:
                statusName = "Srebrny";
                break;
            case PLATINUM:
                statusName = "Platynowy";
                break;
            default:
                statusName = "NN";
        }
        return String.format("%s - %s", name, statusName);*/
        String statusName = status.getStatusName();
        return String.format("%s - %s", name, statusName);
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public  String getNumber() {
        return number;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public Money getBalance() {
        return balance;
    }
}
