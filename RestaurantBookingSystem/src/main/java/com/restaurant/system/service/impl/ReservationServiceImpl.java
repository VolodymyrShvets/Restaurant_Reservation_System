package com.restaurant.system.service.impl;

import com.restaurant.system.model.Administrator;
import com.restaurant.system.model.Customer;
import com.restaurant.system.model.Reservation;
import com.restaurant.system.model.RestaurantTable;
import com.restaurant.system.repository.AdministratorRepository;
import com.restaurant.system.repository.CustomerRepository;
import com.restaurant.system.repository.ReservationRepository;
import com.restaurant.system.repository.TableRepository;
import com.restaurant.system.service.api.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;
    private CustomerRepository customerRepository;
    private TableRepository tableRepository;

    @Override
    public Reservation createReservation(Reservation reservation) {
        log.info("Creating new Reservation with id {}", reservation.getID());

        if (reservationRepository.existsById(reservation.getID()))
            throw new IllegalArgumentException(); // TODO create ReservationAlreadyExists exception

        Optional<Customer> customer = customerRepository.findById(reservation.getCustomer().getID());

        if (customer.isEmpty())
            throw new IllegalArgumentException();

        // check if the customer already has a reservation
        if (customer.get().getReservations().size() == 1) {//.stream().anyMatch(reserv -> !reserv.getReservationTime().isBefore(reservation.getReservationTime()))) {
            throw new IllegalStateException("Customer already has a reservation.");
        }

        Optional<RestaurantTable> table = tableRepository.findById(reservation.getTable().getID());

        if (table.isEmpty())
            throw new IllegalArgumentException();

        System.out.println("before");

        customer.get().getReservations().add(reservation);
        reservation.setCustomer(customer.get());
        table.get().setAdministrator(table.get().getAdministrator());
        reservation.setTable(table.get());

        System.out.println(reservation);
        reservationRepository.save(reservation);

        log.info("Reservation for customer {} successfully created", customer.get().getID());
        return reservation;
    }

    @Override
    public Reservation getReservationByTableID(long tableId) {
        log.info("Getting Reservation with tableId {}", tableId);

        Reservation reservation = reservationRepository.findByTableID(tableId);
        if (reservation == null)
            throw new IllegalArgumentException(); // TODO

        return reservation;
    }

    @Override
    public Reservation getReservationByCustomerID(long customerId) {
        log.info("Getting Reservation with customerId {}", customerId);

        Reservation reservation = reservationRepository.findByCustomerID(customerId);
        if (reservation == null)
            throw new IllegalArgumentException(); // TODO

        return reservation;
    }

    @Override
    public void deleteReservation(long id) {
        log.info("Deleting the Reservation with id {}", id);
        reservationRepository.deleteById(id);
    }
}
