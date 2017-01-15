package pl.com.bottega.photostock.sales.model.money;

/**
 * Created by anna on 18.12.2016.
 */
public interface Money extends Comparable<Money> {
    Money opposite();

    Money add(Money addend);

    Money subtract(Money subtrahend);

    Money multiply(long factor);

    enum Currency {CREDIT;}

    Currency DEFAULT_CURRENCY = Currency.CREDIT;

    RationalMoney convertToRational();

    IntegerMoney convertToInteger();

    Money ZERO = valueOf(0, DEFAULT_CURRENCY);

    static Money valueOf(Rational value, Currency currency) {
        return new RationalMoney(value, currency);
    }

    static Money valueOf(long value, Currency currency) {
        return new RationalMoney(Rational.valueOf(value), currency);
    }

    static Money valueOf(long value) {
        return new RationalMoney(Rational.valueOf(value), DEFAULT_CURRENCY);
    }

    static Money valueOf(float value) {
        return new IntegerMoney((long) (value * 100.0), DEFAULT_CURRENCY);
    }
    //100.00 CREDIT
    static Money valueOf(String moneyString) {
        String[] moneyComponents = moneyString.split(" ");
        if (moneyComponents.length != 1 && moneyComponents.length != 2)
            throw new IllegalArgumentException("Invalid money format");
        long value = (long) (Double.parseDouble(moneyComponents[0]) * 100.0);
        if (moneyComponents.length == 2) {
            return new IntegerMoney(value, Currency.valueOf(moneyComponents[1]));
        }
        else {
            return new IntegerMoney(value, DEFAULT_CURRENCY);
        }
    }

    default boolean gte(Money other) {//greater than or equals
        //int i = value.compareTo(other.value);
        //return i >= 0;
        return compareTo(other) >= 0;
    }

    default boolean gt(Money other) {//greater than
        //int i = value.compareTo(other.value);
        //return i > 0;
        return compareTo(other) > 0;
    }

    default boolean lte(Money other) {//least than or equals
        //int i = value.compareTo(other.value);
        //return i <= 0;
        return compareTo(other) <= 0;
    }

    default boolean lt(Money other) {//least than
        //int i = value.compareTo(other.value);
        //return i < 0;
        return compareTo(other) < 0;
    }
}
