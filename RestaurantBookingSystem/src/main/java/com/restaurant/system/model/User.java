package com.restaurant.system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Setter
@Getter
@Component
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String password;

    User(String id) {
        ID = Integer.parseInt(id);
    }
}
