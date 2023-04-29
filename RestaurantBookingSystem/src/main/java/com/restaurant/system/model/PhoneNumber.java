package com.restaurant.system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class PhoneNumber {
    @Id
    private long ID;

    private String phoneNumber;

    @OneToOne(mappedBy = "phoneNumber")
    private Restaurant restaurant;

    @OneToOne(mappedBy = "phoneNumber")
    private User user;
}
