package com.restaurant.system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Component
public class Feedback {
    @Id
    private long ID;

    @ManyToOne
    @JoinColumn(name = "user_ID", nullable = false)
    private User user;

    private LocalDateTime creationDate;

    private String message;

    //private RATING;
}
