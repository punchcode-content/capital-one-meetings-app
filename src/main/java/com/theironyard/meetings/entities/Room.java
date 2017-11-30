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

    public Room() {
        this.hasProjector = false;
        this.hasTeleconferencing = false;
        this.isReconfigurable = false;
    }

    public Room(String building, String name, Integer maxPeople) {
        this.building = building;
        this.name = name;
        this.maxPeople = maxPeople;
        this.hasProjector = false;
        this.hasTeleconferencing = false;
        this.isReconfigurable = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public Boolean getHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(Boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public Boolean getHasTeleconferencing() {
        return hasTeleconferencing;
    }

    public void setHasTeleconferencing(Boolean hasTeleconferencing) {
        this.hasTeleconferencing = hasTeleconferencing;
    }

    public Boolean getReconfigurable() {
        return isReconfigurable;
    }

    public void setReconfigurable(Boolean reconfigurable) {
        isReconfigurable = reconfigurable;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
