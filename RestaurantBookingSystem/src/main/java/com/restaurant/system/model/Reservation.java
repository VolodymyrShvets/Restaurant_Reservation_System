package com.restaurant.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Component
public class Reservation {
    @Id
    private long ID;

    @ManyToOne
    @JoinColumn(name = "restaurant_table_ID", nullable = false)
    private RestaurantTable table;

    @ManyToOne
    @JoinColumn(name = "user_ID", nullable = false)
    private User customer;

    private LocalDateTime reservationTime;
}
