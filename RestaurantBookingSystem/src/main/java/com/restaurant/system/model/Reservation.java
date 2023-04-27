package com.restaurant.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private long tableID;
    private long customerID;
    private LocalDateTime reservationTime;
}
