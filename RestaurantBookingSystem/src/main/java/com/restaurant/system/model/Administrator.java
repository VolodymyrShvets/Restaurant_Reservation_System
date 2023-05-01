package com.restaurant.system.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@DiscriminatorValue("administrator")
@NoArgsConstructor
public class Administrator extends User {
    Administrator(String id) {
        super(id);
    }

    @OneToMany(mappedBy = "administrator")
    private List<RestaurantTable> tables;
}
