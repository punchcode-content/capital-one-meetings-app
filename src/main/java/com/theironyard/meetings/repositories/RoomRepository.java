package com.theironyard.meetings.repositories;

import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.theironyard.meetings.entities.Building;
import com.theironyard.meetings.entities.QRoom;
import com.theironyard.meetings.entities.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RepositoryRestResource(excerptProjection = DefaultProjection.class)
public interface RoomRepository extends JpaRepository<Room, Integer>,
        QueryDslPredicateExecutor<Room>,
        QuerydslBinderCustomizer<QRoom> {

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

    @RestResource(path = "maxPeopleAtLeast")
    List<Room> findAllByMaxPeopleGreaterThanEqual(@Param("maxPeople") Integer maxPeople);

    @Override
    default void customize(final QuerydslBindings bindings, final QRoom qRoom) {
        SingleValueBinding<NumberPath<Integer>, Integer> maxPeopleBinding =
                NumberExpression::goe;
        bindings.bind(qRoom.maxPeople).first(maxPeopleBinding);
    }
}

interface DefaultProjection {
    Building getBuilding();

    String getName();

    Integer getMaxPeople();

    Boolean getHasProjector();

    Boolean getHasTeleconferencing();

    Boolean getIsReconfigurable();
}
