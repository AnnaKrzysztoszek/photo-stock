package pl.com.bottega.photostock.sales.model;

/**
 * Created by anna on 17.12.2016.
 */
public class Clip extends AbstractProduct {

    private static final Long FIVE_MINUTES = 1000l * 60 * 5;
    private Long length;

    public Clip(String number, String name, Long length, RationalMoney catalogPrice, boolean active) {
        super(catalogPrice, active, number, name);
        this.length = length;
    }
    public Clip(String number, String name, Long length, RationalMoney catalogPrice) {
        this(number, name, length, catalogPrice, true);
    }

    @Override
    public RationalMoney calculatePrice(Client client) {
        if (length > FIVE_MINUTES)
            return catalogPrice.multiply(2);
        else
            return catalogPrice;
    }
}
