package com.theironyard.meetings.repositories;

import com.theironyard.meetings.entities.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    void delete(Integer id);

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    void delete(Room room);

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    void delete(Iterable<? extends Room> rooms);

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    void deleteAll();

    @RestResource(path = "byBuilding")
    List<Room> findAllByBuilding(@Param("building") String building, Pageable page);

    @RestResource(path = "fullyEquipped")
    @Query("SELECT r FROM Room r WHERE has_projector = true and has_teleconferencing = true")
    List<Room> findAllFullyEquipped(Pageable page);
}
