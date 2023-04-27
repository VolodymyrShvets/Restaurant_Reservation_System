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
public class Restaurant {
    @Id
    private long ID;
    private Address address;
    //private Owner owner;
    private LocalDateTime dateOfEstablishment;
    private String name;
    private PhoneNumber phoneNumber;
    //private RATING;
}
