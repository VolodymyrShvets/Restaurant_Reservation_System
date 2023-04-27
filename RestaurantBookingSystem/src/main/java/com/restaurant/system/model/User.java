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
public class User {
    @Id
    private long ID;
    private String firstName;
    private String lastName;
    private PhoneNumber phoneNumber;
    private String email;
    private String password;
    private UserRole role;
}
