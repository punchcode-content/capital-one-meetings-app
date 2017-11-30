package com.theironyard.meetings.repositories;

import com.theironyard.meetings.entities.Building;
import com.theironyard.meetings.entities.Reservation;
import com.theironyard.meetings.entities.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles({"testdb"})
public class ReservationRepositoryTests {
    @Autowired private BuildingRepository buildingRepository;
    @Autowired private RoomRepository roomRepository;
    @Autowired private ReservationRepository reservationRepository;

    @Test
    public void testCanFindOverlappingReservations() {
        Building building = new Building();
        building.setName("Test bldg");
        building = buildingRepository.save(building);

        Room room = new Room();
        room.setName("Test room");
        room.setMaxPeople(2);
        room.setBuilding(building);
        room = roomRepository.save(room);

        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setStartsAt(new Timestamp(2017, 1, 1, 1, 0, 0, 0));
        reservation.setEndsAt(new Timestamp(2017, 1, 1, 2, 0, 0, 0));
        reservation = reservationRepository.save(reservation);

        List<Reservation> overlaps = reservationRepository.findOverlappingReservations(
                reservation.getRoom().getId(),
                new Timestamp(2017, 1, 1, 1, 30, 0, 0),
                new Timestamp(2017, 1, 1, 2, 30, 0, 0)
        );

        assertNotNull(overlaps);
        assertTrue(!overlaps.isEmpty());
    }

    @Test
    public void testCanFindOverlappingReservationsFromReservation() {
        Building building = new Building();
        building.setName("Test bldg");
        building = buildingRepository.save(building);

        Room room = new Room();
        room.setName("Test room");
        room.setMaxPeople(2);
        room.setBuilding(building);
        room = roomRepository.save(room);

        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setStartsAt(new Timestamp(2017, 1, 1, 1, 0, 0, 0));
        reservation.setEndsAt(new Timestamp(2017, 1, 1, 2, 0, 0, 0));
        reservation = reservationRepository.save(reservation);

        Reservation overlappingReservation = new Reservation();
        overlappingReservation.setRoom(room);
        overlappingReservation.setStartsAt(new Timestamp(2017, 1, 1, 1, 30, 0, 0));
        overlappingReservation.setEndsAt(new Timestamp(2017, 1, 1, 3, 30, 0, 0));

        List<Reservation> overlaps = reservationRepository.findOverlappingReservations(
                overlappingReservation
        );

        assertNotNull(overlaps);
        assertTrue(!overlaps.isEmpty());
    }
}
