package com.restaurant.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Setter
@Getter
@Component
@jakarta.persistence.Table(name = "Table")
public class Table {
    @Id
    private long ID;

    private long administratorID;

    private long restaurantID;

    private int numberOfSeats;

    @Enumerated(EnumType.STRING)
    private Place place;
}
