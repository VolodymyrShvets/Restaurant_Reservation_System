package com.restaurant.system.repository;

import com.restaurant.system.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findByCustomerID(long id);

    Reservation findByTableID(long id);
}
