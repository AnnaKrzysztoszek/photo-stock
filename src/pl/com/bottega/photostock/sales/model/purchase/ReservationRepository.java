package pl.com.bottega.photostock.sales.model.purchase;

import pl.com.bottega.photostock.sales.model.purchase.Reservation;

/**
 * Created by anna on 08.01.2017.
 */
public interface ReservationRepository {

    void put(Reservation reservation);

    Reservation get(String reservationNumber);

    Reservation getActiveReservationForClient(String clientNumber);
}
