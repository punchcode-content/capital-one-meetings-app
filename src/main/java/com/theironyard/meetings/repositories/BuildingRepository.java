package com.theironyard.meetings.repositories;

import com.theironyard.meetings.entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Integer> {
}
