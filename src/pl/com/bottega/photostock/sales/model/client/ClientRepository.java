package pl.com.bottega.photostock.sales.model.client;

import pl.com.bottega.photostock.sales.model.client.Client;

/**
 * Created by anna on 08.01.2017.
 */
public interface ClientRepository {

    Client get(String clientNumber);
}
