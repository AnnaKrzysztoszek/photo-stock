package pl.com.bottega.photostock.sales.model;

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

    Money ZERO = RationalMoney.valueOf(0, DEFAULT_CURRENCY);
}
