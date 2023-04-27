package com.restaurant.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Address {
    @Id
    private long ID;
    private String country;
    private String city;
    private String location; // street + building
}
