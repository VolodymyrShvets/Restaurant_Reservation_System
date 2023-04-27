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
public class Feedback {
    @Id
    private long ID;
    private long userID;
    private LocalDateTime creationDate;
    private String message;
    //private RATING;
}
