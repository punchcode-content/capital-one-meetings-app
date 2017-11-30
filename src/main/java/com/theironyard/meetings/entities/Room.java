package com.theironyard.meetings.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(nullable = false)
    private String building;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @Min(1)
    @Column(nullable = false)
    private Integer maxPeople;

    private Boolean hasProjector;
    private Boolean hasTeleconferencing;
    private Boolean isReconfigurable;

    @Column(columnDefinition = "TEXT")
    private String notes;
}
