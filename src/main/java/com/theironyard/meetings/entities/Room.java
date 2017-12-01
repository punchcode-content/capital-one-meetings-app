package com.theironyard.meetings.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "building_id")
    private Building building;

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

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;

    public Room() {
        this.setHasProjector(false);
        this.setHasTeleconferencing(false);
        this.setReconfigurable(false);
    }

    public Room(Building building, String name, Integer maxPeople) {
        this.setBuilding(building);
        this.setName(name);
        this.setMaxPeople(maxPeople);
        this.setHasProjector(false);
        this.setHasTeleconferencing(false);
        this.setReconfigurable(false);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
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

    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof Room && id != null && id.equals(((Room) o).id));
    }
}
