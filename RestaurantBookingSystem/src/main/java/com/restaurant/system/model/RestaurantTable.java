package com.restaurant.system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Setter
@Getter
@Component
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID", referencedColumnName = "restaurant_table.ID")
    private User administrator;

    @ManyToOne
    @JoinColumn(name = "restaurant_ID", nullable = false)
    private Restaurant restaurant;

    private int numberOfSeats;

    @Enumerated(EnumType.STRING)
    private Place place;
}
