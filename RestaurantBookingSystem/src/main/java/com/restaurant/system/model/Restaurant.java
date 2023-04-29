package com.restaurant.system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Component
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_ID", referencedColumnName = "ID")
    private Address address;

    //private Owner owner;

    private LocalDateTime dateOfEstablishment;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phoneNumber_ID", referencedColumnName = "ID")
    private PhoneNumber phoneNumber;

    //private RATING;

    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantTable> tables;
}
