package com.restaurant.system.service.api;

import com.restaurant.system.model.Reservation;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);

    Reservation getReservationByTableID(long tableId); // TODO maybe search by user email

    Reservation getReservationByCustomerID(long customerId);

    void deleteReservation(long id); // TODO maybe search by user email
}
