package com.theironyard.meetings.repositories;

import com.theironyard.meetings.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    void delete(Integer id);

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    void delete(Room room);

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    void deleteAll();

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    void delete(Iterable<? extends Room> rooms);
}
