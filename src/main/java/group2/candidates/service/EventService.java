package group2.candidates.service;

import group2.candidates.model.data.Event;
import group2.candidates.repository.EventRepository;
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

    private List<Event> getEventInMonth(String year_month, List<Event> events){
        List<Event> eventListResult = new ArrayList<>();
        for (Event event : events) {
            if(event.getPlannedStartDate().toString().contains(year_month)
                    || event.getPlannedEndDate().toString().contains(year_month)
                    || event.getActualStartDate().toString().contains(year_month)
                    || event.getActualEndDate().toString().contains(year_month)){
                eventListResult.add(event);
            }
        }
        return eventListResult;
    }

    /**
     * get all planning events.
     * @return list of planning event.
     */
    public Collection<Event> getRecentEvents(){
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        String year_month =  year + "-";
        if(month > 9){
            year_month+= month;
        }else{
            year_month+= "0" + month;
        }
        List<Event> eventList = repository.findAll();
        System.out.println(year_month);
        return getEventInMonth(year_month, eventList);
    }

    /**
     * get all event in a particular month and year.
     * @param month is the month.
     * @param year is the year.
     * @return all of event in that month and that year.
     */
    public Collection<Event> getAllEventsInMonth(String year, String month){
        String year_month =  year + "-";
        if(month.length() > 1){
            year_month+= month;
        }else{
            year_month+= "0" + month;
        }
        List<Event> eventList = repository.findAll();
        return getEventInMonth(year_month, eventList);
    }

    /**
     * get all events in a particular week.
     * @param startDate the start date of the week.
     * @param endDate the end date of the week.
     * @return list of all events in that week.
     */
    public Collection<Event> getAllEventsInWeek(String startDate, String endDate){
        List<Event> eventList = repository.findAll();
        List<Event> eventInMonthList = new ArrayList<>();
        for (Event event : eventList) {
            if((event.getPlannedStartDate().toString().compareTo(endDate) <= 0
                    && event.getPlannedStartDate().toString().compareTo(startDate) >= 0)
            || (event.getActualStartDate().toString().compareTo(endDate) <= 0
                    && event.getActualStartDate().toString().compareTo(startDate) >= 0)) {
                eventInMonthList.add(event);
            }
        }
        return eventInMonthList;

    }
}
