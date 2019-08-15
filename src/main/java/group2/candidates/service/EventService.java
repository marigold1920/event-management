package group2.candidates.service;

import group2.candidates.model.data.Event;
import group2.candidates.repository.EventRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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
    
    /**
     *  get all events.
     * @return list All events.
     */
    public List<Event> getAllEvents(){
        return repository.findAll();
    }


    /**
     * get all event in a particular month.
     * @param firstDate the first date of the month.
     * @param lastDate the first date of the next month(it's used to get the range of the particular month).
     * @return
     */
    public Collection<Event> getAllEventsInMonth(LocalDate firstDate, LocalDate lastDate){
        return repository.findEventsInMonth(firstDate, lastDate);
    }

    /**
     * get all events in a particular week.
     * @param startDate the start date of the week.
     * @param endDate the end date of the week.
     * @return list of all events in that week.
     */
    public Collection<Event> getAllEventsInWeek(LocalDate startDate, LocalDate endDate){
            return repository.findEventsInWeek(startDate , endDate);
    }

    /**
     * get all event of a supplier in a range of date.
     * @param startDate the start date of range.
     * @param endDate the end date of range.
     * @param supplierId the university Id.
     * @return
     */
    public Collection<Event> getEventInRangOfUniversity(LocalDate startDate, LocalDate endDate, Integer supplierId){
        Collection<Event> eventList = repository.findEventsInWeek(startDate , endDate);
        List<Event> resultList = new ArrayList<>();
        for (Event event: eventList) {
            if(event.getSupplier().getUniversityId() == supplierId){
                resultList.add(event);
            }
        }
        return resultList;
    }
}
