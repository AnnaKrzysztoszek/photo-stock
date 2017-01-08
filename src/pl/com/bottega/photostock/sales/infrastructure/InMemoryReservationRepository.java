package pl.com.bottega.photostock.sales.infrastructure;

import pl.com.bottega.photostock.sales.model.Reservation;
import pl.com.bottega.photostock.sales.model.ReservationRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anna on 08.01.2017.
 */
public class InMemoryReservationRepository implements ReservationRepository {

    private static final Map<String, Reservation> REPOSITORY = new HashMap<>();

    @Override
    public void put(Reservation reservation) {
        REPOSITORY.put(reservation.getNumber(), reservation);
    }

    @Override
    public Reservation get(String reservationNumber) {
        return REPOSITORY.get(reservationNumber);
    }

    @Override
    public Reservation getActiveReservationForClient(String clientNumber) {
        //pętl która iteruje o wszystkich rezerwacjach, czy jest dla danego klienta jesli nie to null
        for (Reservation reservation : REPOSITORY.values()) {
            if (reservation.isOwnedBy(clientNumber))
                return reservation;
        }
        return null;
    }
}
