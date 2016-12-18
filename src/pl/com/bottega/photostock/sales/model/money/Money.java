package pl.com.bottega.photostock.sales.model.money;

import pl.com.bottega.photostock.sales.model.Rational;

/**
 * Created by anna on 18.12.2016.
 */
public interface Money extends Comparable<Money> {
    Money opposite();

    Money add(Money addend);

    Money subtract(Money subtrahend);

    Money multiply(long factor);

    boolean gte(Money other);

    boolean gt(Money other);

    boolean lte(Money other);

    boolean lt(Money other);

    enum Currency {CREDIT;}

    Currency DEFAULT_CURRENCY = Currency.CREDIT;

    RationalMoney convertToRational();

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
}
