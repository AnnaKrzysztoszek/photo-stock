package pl.com.bottega.photostock.sales.model.client;

/**
 * Created by anna on 11.12.2016.
 */
public class CantAffordException extends RuntimeException {


    public CantAffordException(String message) {
        super(message);//super wywołuje konstruktor klasy nadrzędnej;
    }
}
