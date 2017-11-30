package com.theironyard.meetings.repositories;

import com.theironyard.meetings.entities.Building;
import com.theironyard.meetings.entities.Room;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoomRepositoryTests {
    @Autowired private RoomRepository roomRepository;
    @Autowired private BuildingRepository buildingRepository;
    private Room room;
    private Building building;

    @Before
    public void saveRoom() {
        Building building = new Building();
        building.setName("Building 1");
        this.building = buildingRepository.save(building);
        Room room = new Room(this.building, "1001", 1);
        this.room = roomRepository.save(room);
    }

    @Test
    public void testCanSaveBook() {
        assertNotNull(room.getId());
    }

    @Test
    public void testFindAll() {
        List<Room> rooms = roomRepository.findAll();
        assertNotNull(rooms);
        assertTrue(!rooms.isEmpty());
    }

    @Test
    public void testCanRetrieveSavedBook() {
        Room retrievedRoom = roomRepository.findOne(room.getId());
        assertNotNull(retrievedRoom.getId());
        assertEquals(retrievedRoom.getId(), room.getId());
        assertEquals(retrievedRoom.getBuilding(), building);
        assertEquals(retrievedRoom.getName(), "1001");
    }

    @Test
    public void testCanDeleteRoom() {
        roomRepository.delete(room.getId());
        Room retrievedRoom = roomRepository.findOne(room.getId());
        assertNull(retrievedRoom);
    }
}
