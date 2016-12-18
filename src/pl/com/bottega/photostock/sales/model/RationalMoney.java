package pl.com.bottega.photostock.sales.model;


/**
 * Created by anna on 10.12.2016.
 */
public class RationalMoney implements Money {

    @Override
    public Money opposite() {
        return valueOf(value.negative(), currency);
    }

    public static Money valueOf(Rational value, Currency currency) {
        return new RationalMoney(value, currency);
    }

    public static Money valueOf(long value, Currency currency) {
        return new RationalMoney(Rational.valueOf(value), currency);
    }

    public static Money valueOf(long value) {
        return new RationalMoney(Rational.valueOf(value), DEFAULT_CURRENCY);
    }

    @Override
    public Money add(Money addend) {
        RationalMoney rationalMoney = addend.convertToRational();
        if (currency != rationalMoney.currency)
            throw new IllegalArgumentException("The currencies do not match.");

        return valueOf(value.add(rationalMoney.value), currency);
    }

    @Override
    public Money subtract(Money subtrahend) {
        RationalMoney rationalMoney = subtrahend.convertToRational();
        if (currency != rationalMoney.currency)
            throw new IllegalArgumentException("The currencies do not match.");

        return valueOf(value.subtract(rationalMoney.value), currency);
    }

    @Override
    public Money multiply(long factor) {
        if (factor < 0)
            throw new IllegalArgumentException("Money cannot be negative");

        return valueOf(value.multiply(factor), currency);
    }

    @Override
    public boolean gte(Money other) {//greater than or equals
        //int i = value.compareTo(other.value);
        //return i >= 0;
        return compareTo(other) >= 0;
    }

    @Override
    public boolean gt(Money other) {//greater than
        //int i = value.compareTo(other.value);
        //return i > 0;
        return compareTo(other) > 0;
    }

    @Override
    public boolean lte(Money other) {//least than or equals
        //int i = value.compareTo(other.value);
        //return i <= 0;
        return compareTo(other) <= 0;
    }

    @Override
    public boolean lt(Money other) {//least than
        //int i = value.compareTo(other.value);
        //return i < 0;
        return compareTo(other) < 0;
    }

    @Override
    public int compareTo(Money other) {
        RationalMoney rationalMoney = other.convertToRational();
        if (!rationalMoney.currency.equals(currency))
            throw new IllegalArgumentException("Currency mismatch");
        return value.compareTo(rationalMoney.value);
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

    @Override
    public RationalMoney convertToRational() {
        return this;
    }
}

