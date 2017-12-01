package com.theironyard.meetings;

import com.theironyard.meetings.entities.Building;
import com.theironyard.meetings.entities.Room;
import com.theironyard.meetings.repositories.BuildingRepository;
import com.theironyard.meetings.repositories.ReservationRepository;
import com.theironyard.meetings.repositories.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SampleDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private BuildingRepository buildingRepository;
    private RoomRepository roomRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public SampleDataLoader(BuildingRepository buildingRepository,
                            RoomRepository roomRepository,
                            ReservationRepository reservationRepository) {
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    private void deleteExistingData() {
        try {
            SecurityContext ctx = SecurityContextHolder.createEmptyContext();
            SecurityContextHolder.setContext(ctx);
            ctx.setAuthentication(new PreAuthenticatedAuthenticationToken("admin", null,
                    AuthorityUtils.createAuthorityList("ROLE_ADMIN")));

            reservationRepository.deleteAll();
            roomRepository.deleteAll();
            buildingRepository.deleteAll();

        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Logger log = LoggerFactory.getLogger(this.getClass());
        log.info("Loading sample data");

        deleteExistingData();

        Building bldg1 = new Building("Building 1");
        bldg1 = buildingRepository.save(bldg1);

        Building bldg2 = new Building("Building 2");
        bldg2 = buildingRepository.save(bldg2);

        Room room1001 = new Room(bldg1, "1001", 12);
        Room room1002 = new Room(bldg1, "1002", 10);
        Room room1010 = new Room(bldg1, "1010", 20);

        Room room2005 = new Room(bldg2, "2005", 8);
        Room room2007 = new Room(bldg2, "2007", 4);
        Room roomAuditorium = new Room(bldg2, "Chavez Auditorium", 80);

        roomRepository.save(
                Arrays.asList(room1001, room1002, room1010, room2005, room2007, roomAuditorium)
        );

        log.info("Sample data loaded");
    }
}
