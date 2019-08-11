package group2.candidates.service;

import group2.candidates.model.data.Event;
import group2.candidates.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private EventRepository repository;

    @Autowired
    public void setRepository(EventRepository repository) {
        this.repository = repository;
    }

    /**
     * Find event by courseCode
     * @param courseCode course code of event
     * @return Optional<Event>
     */
   public Optional<Event> findEventByCourseCode(String courseCode) {

       return repository.findEventByCourseCode(courseCode);
   }

    /**
     * Find Event by eventId
     * @param eventId id of event
     * @return Optional<Event>
     */
    public Optional<Event> findEventByEventId(int eventId) {

        return repository.findById(eventId);
    }

    /**
     * Load events from Database, must be do Pagination, page size 10
     * @param paginationIndex page index
     */
    public List<Event> loadEvents(int paginationIndex) {

        return repository
                .findAll(PageRequest.of(paginationIndex - 1, 10))
                .getContent();
    }

    /**
     * Search event by Course Name (contains search) and do Pagination
     * @param paginationIndex page index
     * @return Stream<Event>
     */
    public Collection<Event> searchEventByName(String keySearch, int paginationIndex) {

        return repository
                .searchEventByName(keySearch, PageRequest.of(paginationIndex - 1, 10))
                .getContent();
    }

    /**
     * Use for add new Event or update Event information
     * @param event event need to add or update
     */
    public Event saveEvent(Event event) {
        
        return repository.saveAndFlush(event);
    }

    /**
     * Change event status
     * Formula: event status is Done, not be allowed, other status, be become Cancelled
     * @param eventId id of event need to be cancelled
     */
    public Integer cancelEvent(int eventId) {

        return repository.changeEventStatusToCancelled(eventId);
    }
}
