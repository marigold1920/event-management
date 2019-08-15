package group2.candidates.controller;

import group2.candidates.adapter.EventAdapter;
import group2.candidates.adapter.SectionAdapter;
import group2.candidates.model.data.Event;
import group2.candidates.service.*;
import group2.candidates.tool.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class EventController {

    private PoolService pool = PoolService.getPoolService();

    private EventService eventService;
    private SubSubjectTypeService subSubjectTypeService;
    private UniversityService universityService;
    private CampusLinkProgramService campusLinkProgramService;
    private DepartmentService departmentService;

    /**
     * Use to save all candidates of event(s)
     * SectionAdapter contains Candidate information, Course code for finding event, Section information
     * After converting json to SectionAdapter, must be call buildSection() to change it into  Section model
     * and link this object to Event and Candidate for making relationship
     * @param sectionAdapters collection of SectionAdapter after converting from json string
     * @see SectionAdapter
     */
    @PostMapping(value = "sections", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public void saveCandidatesOfEventFromExcelFile(@RequestBody Collection<SectionAdapter> sectionAdapters) {
        sectionAdapters.stream().collect(Collectors.groupingBy(SectionAdapter::getCourseCode, Collectors.toList()))
                .forEach((k, v) -> eventService.findEventByCourseCode(k).ifPresent(e -> {
                    e.getCandidates().addAll(
                            v.stream().map(ca -> ca.buildSection(e, departmentService)).collect(Collectors.toList()));
                    eventService.saveEvent(e);
                }));
        pool.destroy();
    }

    /**
     * Use to save all event(s)
     * @param eventAdapters collection of EventAdapter after converting from json string
     * @return Collection<Integer> after saving, all event must be returned eventId
     */
    @PostMapping(value = "events", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Integer> saveEventsFromExcelFile(@RequestBody Collection<EventAdapter> eventAdapters) {

        return eventAdapters.stream()
                .map(e -> e.buildEvent(subSubjectTypeService, universityService, campusLinkProgramService))
                .map(eventService::saveEvent)
                .map(Event::getEventId)
                .collect(Collectors.toList());
    }

    /**
     * Use to save an event
     * @param eventAdapter EventAdapter after converting from json string
     * @return Integer after saving, event must be returned eventId
     */
    @PostMapping(value = "event", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Integer saveEventFromManual(@RequestBody EventAdapter eventAdapter) {

        return eventService.saveEvent(
                eventAdapter.buildEvent(subSubjectTypeService, universityService, campusLinkProgramService))
                .getEventId();
    }

    /**
     *
     * @param eventId
     * @return
     */
    @GetMapping(value = "event", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Event getEventById(@Param("eventId") Integer eventId) {
        var event = eventService.findEventByEventId(eventId).orElse(Event.builder().build());

        return event;
    }

    /**
     * Load events, page size: 10
     * @param paginationIndex page number
     * @return Collection<Event>, will be converted to json by RestController
     */
    @GetMapping(value = "events/{paginationIndex}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Event> loadEvents(@PathVariable int paginationIndex) {
        if (paginationIndex < 1)
            return new ArrayList<>();

        return eventService.loadEvents(paginationIndex);
    }

    /**
     * Search events base on Course Code of event, page size: 10
     * Formula: contains, ignore case
     * @param keySearch key for searching
     * @param paginationIndex page number
     * @return Collection<Event>
     */
    @GetMapping(value = "searchResults/{paginationIndex}", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Event> searchEvent(@Param("keySearch") String keySearch, @PathVariable int paginationIndex) {
        if (paginationIndex < 1)  return new ArrayList<>();

        return eventService.searchEventByName(keySearch, paginationIndex);
    }

    /**
     * Update status events
     * Formula: setting 'Cancel ' for all events getting from eventId
     * @param eventId array of eventId
     * @return  Collection<Event>, events changed status
     */
    @PatchMapping("events")
    public Collection<Integer> cancelEvent(@Param("eventId") Integer[] eventId) {

        return Arrays.stream(eventId)
                .map(eventService::cancelEvent)
                .collect(Collectors.toList());
    }

    /**
     * Use to update event information
     * @param eventAdapter use to build Event
     * @return Event after updating
     */
    @PutMapping(value = "events", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Event updateEventInformation(@RequestBody EventAdapter eventAdapter) {
        Objects.requireNonNull(eventAdapter);

        return eventService
                .saveEvent(eventAdapter.buildEvent(subSubjectTypeService, universityService, campusLinkProgramService));
    }

    @GetMapping(value = "events/recent", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Event> getRecentEvents(){
        return eventService.getRecentEvents();
    }

    @GetMapping(value = "events-month/{year}/{month}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Event> getALlEventsInMonth(@PathVariable("year") String year, @PathVariable("month") String month){
        return eventService.getAllEventsInMonth(year, month);
    }

    @GetMapping(value = "events-week/{start-date}/{end-date}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Event> getAllEventInWeek(@PathVariable("start-date") String startDate, @PathVariable("end-date") String endDate){
        return eventService.getAllEventsInWeek(startDate, endDate);
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setCampusLinkProgramService(CampusLinkProgramService campusLinkProgramService) {
        this.campusLinkProgramService = campusLinkProgramService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    public void setSubSubjectTypeService(SubSubjectTypeService subSubjectTypeService) {
        this.subSubjectTypeService = subSubjectTypeService;
    }

    @Autowired
    public void setUniversityService(UniversityService universityService) {
        this.universityService = universityService;
    }
}
