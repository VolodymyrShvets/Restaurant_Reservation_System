package com.restaurant.system.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private String country;
    private String city;
    private String location; // street + building
}
