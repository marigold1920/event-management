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
     * get all events in this month.
     * @return list of planning event.
     */
    public Collection<Event> getRecentEvents(){

        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        return getEvents(year, month);
    }

    private Collection<Event> getEvents(int year, int month) {
        LocalDate firstDate = LocalDate.of(year, month,1);
        LocalDate lastDate = null;
        if(month != 12) {
            lastDate = LocalDate.of(year, month + 1, 1);
        }else{
            lastDate = LocalDate.of(year + 1, 1, 1);
        }
        Collection<Event> eventList = repository.findEventsInMonth(firstDate, lastDate);
        return eventList;
    }

    /**
     * get all event in a particular month and year.
     * @param month is the month.
     * @param year is the year.
     * @return all of event in that month and that year.
     */
    public Collection<Event> getAllEventsInMonth(int year, int month){
        return getEvents(year, month);
    }

    /**
     * get all events in a particular week.
     * @param startDate the start date of the week.
     * @param endDate the end date of the week.
     * @return list of all events in that week.
     */
    public Collection<Event> getAllEventsInWeek(String startDate, String endDate){
        String[] start = startDate.split("-");
        String[] end = endDate.split("-");
        LocalDate startLocalDate = LocalDate.of(Integer.parseInt(start[0]), Integer.parseInt(start[1]), Integer.parseInt(start[2]));
        LocalDate endLocalDate = LocalDate.of(Integer.parseInt(end[0]), Integer.parseInt(end[1]), Integer.parseInt(end[2]));
        Collection<Event> eventList = repository.findEventsInWeek(startLocalDate , endLocalDate);
        return eventList;

    }
}
