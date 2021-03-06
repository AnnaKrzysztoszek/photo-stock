package pl.com.bottega.photostock.sales.model.money;


/**
 * Created by anna on 10.12.2016.
 */
class RationalMoney implements Money {

    @Override
    public Money opposite() {
        return new RationalMoney(value.negative(), currency);
    }

    @Override
    public Money add(Money addend) {
        RationalMoney rationalMoney = addend.convertToRational();
        if (currency != rationalMoney.currency)
            throw new IllegalArgumentException("The currencies do not match.");

        return new RationalMoney(value.add(rationalMoney.value), currency);
    }

    @Override
    public Money subtract(Money subtrahend) {
        RationalMoney rationalMoney = subtrahend.convertToRational();
        if (currency != rationalMoney.currency)
            throw new IllegalArgumentException("The currencies do not match.");

        return new RationalMoney(value.subtract(rationalMoney.value), currency);
    }

    @Override
    public Money multiply(long factor) {
        if (factor < 0)
            throw new IllegalArgumentException("Money cannot be negative");

        return new RationalMoney(value.multiply(factor), currency);
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

    RationalMoney(Rational value, Currency currency) {
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

    @Override
    public IntegerMoney convertToInteger() {
        return new IntegerMoney(value.getNumerator() * 100L / value.getDenominator(), currency);
    }
}

