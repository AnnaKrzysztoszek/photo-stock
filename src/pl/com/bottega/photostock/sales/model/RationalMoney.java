package pl.com.bottega.photostock.sales.model;


/**
 * Created by anna on 10.12.2016.
 */
public class RationalMoney implements Money {

    private static final Currency DEFAULT_CURRENCY = Currency.CREDIT;

    public static final RationalMoney ZERO = valueOf(0, DEFAULT_CURRENCY);

    @Override
    public Money opposite() {
        return valueOf(value.negative(), currency);
    }

    public static RationalMoney valueOf(Rational value, Currency currency) {
        return new RationalMoney(value, currency);
    }

    public static RationalMoney valueOf(long value, Currency currency) {
        return new RationalMoney(Rational.valueOf(value), currency);
    }

    public static RationalMoney valueOf(long value) {
        return new RationalMoney(Rational.valueOf(value), DEFAULT_CURRENCY);
    }

    @Override
    public RationalMoney add(RationalMoney addend) {
        if (currency != addend.currency)
            throw new IllegalArgumentException("The currencies do not match.");

        return valueOf(value.add(addend.value), currency);
    }

    @Override
    public Money subtract(RationalMoney subtrahend) {
        if (currency != subtrahend.currency)
            throw new IllegalArgumentException("The currencies do not match.");

        return valueOf(value.subtract(subtrahend.value), currency);
    }

    @Override
    public RationalMoney multiply(long factor) {
        if (factor < 0)
            throw new IllegalArgumentException("Money cannot be negative");

        return valueOf(value.multiply(factor), currency);
    }

    @Override
    public boolean gte(RationalMoney other) {//greater than or equals
        //int i = value.compareTo(other.value);
        //return i >= 0;
        return compareTo(other) >= 0;
    }

    @Override
    public boolean gt(RationalMoney other) {//greater than
        //int i = value.compareTo(other.value);
        //return i > 0;
        return compareTo(other) > 0;
    }

    @Override
    public boolean lte(RationalMoney other) {//least than or equals
        //int i = value.compareTo(other.value);
        //return i <= 0;
        return compareTo(other) <= 0;
    }

    @Override
    public boolean lt(RationalMoney other) {//least than
        //int i = value.compareTo(other.value);
        //return i < 0;
        return compareTo(other) < 0;
    }

    @Override
    public int compareTo(RationalMoney other) {
        if (!other.currency.equals(currency))
            throw new IllegalArgumentException("Currency mismatch");
        return value.compareTo(other.value);
    }

    private final Rational value;
    private final Currency currency;

    private RationalMoney(Rational value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return value.toDouble() + " " + currency.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RationalMoney)) return false;

        RationalMoney money = (RationalMoney) o;

        if (!value.equals(money.value)) return false;
        return currency == money.currency;
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }
}

