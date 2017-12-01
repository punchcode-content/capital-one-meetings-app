package com.theironyard.meetings.entities;

import org.springframework.data.rest.core.config.Projection;

import javax.persistence.*;
import java.util.List;

@Projection(name = "withRooms", types = {Building.class})
interface WithRooms {
    String getName();

    List<Room> getRooms();
}

@Entity
@Table(name = "buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "building")

    private List<Room> rooms;

    public Building() {
    }

    public Building(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof Building && id != null && id.equals(((Building) o).id));
    }
}
