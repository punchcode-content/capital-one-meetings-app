package com.theironyard.meetings.repositories;

import com.theironyard.meetings.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import java.sql.Timestamp;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    void delete(Iterable<? extends Reservation> reservations);

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    void deleteAll();

    @Query(nativeQuery = true,
            value = "SELECT * FROM reservations r " +
                    "WHERE tsrange(r.starts_at, r.ends_at, '()') && " +
                    "tsrange(:starts_at, :ends_at, '()') AND " +
                    "r.room_id = :room_id"
    )
    List<Reservation> findOverlappingReservations(@Param("room_id") Integer roomId,
                                                  @Param("starts_at") Timestamp startsAt,
                                                  @Param("ends_at") Timestamp endsAt);

    default List<Reservation> findOverlappingReservations(Reservation reservation) {
        if (reservation.getRoom() == null ||
                reservation.getStartsAt() == null ||
                reservation.getEndsAt() == null) {
            return null;
        }
        return findOverlappingReservations(reservation.getRoom().getId(),
                reservation.getStartsAt(), reservation.getEndsAt());
    }
}
