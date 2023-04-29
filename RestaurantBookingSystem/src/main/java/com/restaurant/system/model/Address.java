package com.restaurant.system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private String country;
    private String city;
    private String location; // street + building

    @OneToOne(mappedBy = "address")
    private Restaurant restaurant;
}
