package group2.candidates.repository;

import group2.candidates.model.data.Event;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("select e from Event  e where e.courseCode like concat('%', :keySearch, '%')")
    Page<Event> searchEventByName(@Param("keySearch") String keySearch, Pageable pageable);

    @Modifying
    @Query("update Event set eventStatus = 'Cancel' where eventId = ?1")
    Integer changeEventStatusToCancelled(int eventId);

    @Query("select e from Event e where e.courseCode = ?1")
	Optional<Event> findEventByCourseCode(String courseCode);

    @Query("SELECT e FROM Event e WHERE e.plannedStartDate >= :first and e.plannedStartDate < :last" +
            " OR e.plannedEndDate >= :first and e.plannedEndDate < :last" +
            " OR e.actualStartDate >= :first and e.actualStartDate < :last" +
            " OR e.actualEndDate >= :first and e.actualEndDate < :last")
    Collection<Event> findEventsInRangeDate(@Param("first") LocalDate firstDate, @Param("last") LocalDate lastDate);
}
