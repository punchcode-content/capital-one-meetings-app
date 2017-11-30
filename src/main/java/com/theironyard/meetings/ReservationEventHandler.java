package com.theironyard.meetings;

import com.theironyard.meetings.entities.Reservation;
import com.theironyard.meetings.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RepositoryEventHandler(Reservation.class)
public class ReservationEventHandler {
    private ReservationRepository reservationRepository;

    @Autowired
    public void setReservationRepository(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @HandleBeforeCreate
    public void addCurrentUserToReservation(Reservation reservation) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        reservation.setReservedBy(name);
    }

    @HandleBeforeCreate
    @HandleBeforeSave
    public void checkForReservationConflict(Reservation reservation) throws ReservationOverlapException {
        List<Reservation> overlaps = reservationRepository.findOverlappingReservations(reservation);
        if (!(overlaps == null || overlaps.isEmpty())) {
            throw new ReservationOverlapException();
        }
    }

    @HandleBeforeDelete
    public void checkDeletingUser(Reservation reservation) throws ReservationSecurityException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!reservation.getReservedBy().equals(name)) {
            throw new ReservationSecurityException();
        }
    }
}
