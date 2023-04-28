package com.restaurant.system.controller;

import com.restaurant.system.model.Reservation;
import com.restaurant.system.service.api.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationService reservationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{tableId}")
    public Reservation getReservationByTableID(@PathVariable long tableId) {
        return reservationService.getReservationByTableID(tableId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{customerId}")
    public Reservation getReservationByCustomerID(@PathVariable long customerId) {
        return reservationService.getReservationByCustomerID(customerId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable long id) {
        reservationService.deleteReservation(id);
    }
}
