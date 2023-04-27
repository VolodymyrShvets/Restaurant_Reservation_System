package com.restaurant.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Setter
@Getter
@Component
public class Table {
    @Id
    private long ID;
    private long administratorID;
    private long restaurantID;
    private int numberOfSeats;
    private Place place;
}
