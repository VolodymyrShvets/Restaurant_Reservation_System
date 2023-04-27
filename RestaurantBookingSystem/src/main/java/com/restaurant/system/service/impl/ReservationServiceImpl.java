package com.restaurant.system.service.impl;

import com.restaurant.system.model.Reservation;
import com.restaurant.system.repository.ReservationRepository;
import com.restaurant.system.service.api.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository repository;

    @Override
    public Reservation createReservation(Reservation reservation) {
        log.info("Creating new Reservation with id {}", reservation.getID());

        if (repository.existsById(reservation.getID()))
            throw new IllegalArgumentException(); // TODO create ReservationAlreadyExists exception

        Reservation newReservation = repository.save(reservation);

        log.info("Reservation with id {} successfully created", reservation.getID());
        return newReservation;
    }

    @Override
    public Reservation getReservationByTableID(long tableId) {
        log.info("Getting Reservation with tableId {}", tableId);

        Reservation reservation = repository.findByTableID(tableId);
        if (reservation == null)
            throw new IllegalArgumentException(); // TODO

        return reservation;
    }

    @Override
    public Reservation getReservationByCustomerID(long customerId) {
        log.info("Getting Reservation with customerId {}", customerId);

        Reservation reservation = repository.findByCustomerID(customerId);
        if (reservation == null)
            throw new IllegalArgumentException(); // TODO

        return reservation;
    }

    @Override
    public void deleteReservation(long id) {
        log.info("Deleting the Reservation with id {}", id);
        repository.deleteById(id);
    }
}
