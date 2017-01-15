package pl.com.bottega.photostock.sales.model.money;

/**
 * Created by anna on 18.12.2016.
 */
class IntegerMoney implements Money {

    private long cents;
    private Currency currency;

    IntegerMoney(long cents, Currency currency) {
        this.cents = cents;
        this.currency = currency;
    }

    @Override
    public Money add(Money addend) {
        IntegerMoney integerMoney = safeConvert(addend);
        return new IntegerMoney(cents + integerMoney.cents, currency);
    }

    @Override
    public Money subtract(Money subtrahend) {
        IntegerMoney integerMoney = safeConvert(subtrahend);
        return new IntegerMoney(cents - integerMoney.cents, currency);
    }

    @Override
    public Money multiply(long factor) {
        return new IntegerMoney(cents * factor, currency);
    }

    @Override
    public Money opposite() {
        return new IntegerMoney(-cents, currency);
    }

    @Override
    public RationalMoney convertToRational() {
        return new RationalMoney(Rational.valueOf(cents, 100), currency);
    }

    @Override
    public IntegerMoney convertToInteger() {
        return this;
    }

    @Override
    public int compareTo(Money o) {
        IntegerMoney integerMoney = safeConvert(o);
        if (cents == integerMoney.cents)
            return 0;
        else if (cents < integerMoney.cents)
            return -1;
        else
            return 1;
        //return cents < integerMoney.cents ? -1 : 1; zamiast else if i else
    }

    private IntegerMoney safeConvert(Money other) {
        IntegerMoney integerMoney = other.convertToInteger();
        ensureSameCurrency(integerMoney);
        return integerMoney;
    }

    private void ensureSameCurrency(IntegerMoney other) {
        if (currency != other.currency)
            throw new IllegalArgumentException("Currency mismatch");
    }

    @Override
    public String toString() {
        return String.format("%d.%02d %s", cents / 100, cents % 100, currency.name());
    }
}
