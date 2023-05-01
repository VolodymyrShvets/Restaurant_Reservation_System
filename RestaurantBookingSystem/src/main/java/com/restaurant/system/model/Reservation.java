package com.restaurant.system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Component
@ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @OneToOne
    @JoinColumn(name = "restaurant_table_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RestaurantTable table;

    @ManyToOne
    @JoinColumn(name = "customer_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Customer customer;

    @Column(nullable = false)
    private LocalDateTime reservationTime;
}
