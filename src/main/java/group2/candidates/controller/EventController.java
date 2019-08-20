package group2.candidates.controller;

import group2.candidates.adapter.EventAdapter;
import group2.candidates.adapter.SectionAdapter;
import group2.candidates.common.ResponseObject;
import group2.candidates.model.data.Event;
import group2.candidates.model.data.Section;
import group2.candidates.service.*;
import group2.candidates.tool.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    private CandidateService candidateService;
    private SectionService sectionService;

    /**
     * Use to save all candidates of event(s)
     * SectionAdapter contains Candidate information, Course code for finding event, Section information
     * After converting json to SectionAdapter, must be call buildSection() to change it into  Section model
     * and link this object to Event and Candidate for making relationship
     * @param sectionAdapters collection of SectionAdapter after converting from json string
     * @see SectionAdapter
     */
    @PostMapping(value = "sections", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public ResponseObject saveCandidatesOfEventFromExcelFile(@RequestBody Collection<SectionAdapter> sectionAdapters) {
        var responseObj = new ResponseObject();
        pool.instantiationCandidates(candidateService.findAllByEmail(sectionAdapters.stream()
                  .map(SectionAdapter::getEmail)
                  .collect(Collectors.toList())
        ));
        pool.instantiationEvents(eventService.findAllByCourseCode(sectionAdapters.stream()
                .map(SectionAdapter::getCourseCode)
                .collect(Collectors.toList())
        ));

        sectionAdapters.stream().collect(Collectors.groupingBy(SectionAdapter::getCourseCode, Collectors.toList()))
                  .forEach((k, v) -> pool.getEvent(k).ifPresentOrElse(e -> {
                      e.getCandidates().addAll(v.stream().map(ca -> ca.buildSection(e, departmentService, responseObj)).filter(Objects::nonNull).collect(Collectors.toList()));
                      responseObj.addIdentifiedData(eventService.saveEvent(e).getCandidates().stream().map(Section::getSectionId).collect(Collectors.toList()));
                  }, () -> responseObj.addErrors("System was not found Event with Course Code: " + k)));
          pool.destroy();

          return responseObj.setStatus();
    }

    @PostMapping(value = "section", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public ResponseObject saveCandidateOfEventFromManual(@RequestBody SectionAdapter sectionAdapter) {
        var responseObj = new ResponseObject();
        pool.instantiationCandidate(candidateService.findCandidateByEmail(sectionAdapter.getEmail()));

        eventService.findEventByCourseCode(sectionAdapter.getCourseCode())
                .ifPresentOrElse(e -> {
                    var section = sectionAdapter.buildSection(e, departmentService, responseObj);
                    if (section != null) sectionService.saveSection(section);
                }, () -> responseObj.addErrors("System was not found Event with Course Code: " + sectionAdapter.getCourseCode()));
        pool.destroy();

        return responseObj.setStatus();
    }

    /**
     * Use to save all event(s)
     * @param eventAdapters collection of EventAdapter after converting from json string
     * @return Collection<Integer> after saving, all event must be returned eventId
     */
    @PostMapping(value = "events", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public ResponseObject saveEventsFromExcelFile(@RequestBody Collection<EventAdapter> eventAdapters) {
        var responseObj = new ResponseObject();
        pool.instantiationCampusLinkPrograms(campusLinkProgramService.loadCampusLinkPrograms());
        pool.instantiationSubSubjectTypes(subSubjectTypeService.loadAllSubSubjectTypes());
        pool.instantiationSuppliers(universityService.loadUniversity());

        responseObj.addIdentifiedData(eventAdapters.stream()
                .map(e -> e.buildEvent(responseObj, eventService))
                .filter(Objects::nonNull)
                .map(eventService::saveEvent)
                .map(Event::getEventId)
                .collect(Collectors.toList()));
        pool.destroy();

        return responseObj.setStatus();
    }

    /**
     * Use to save an event
     * @param eventAdapter EventAdapter after converting from json string
     * @return Integer after saving, event must be returned eventId
     */
    @PostMapping(value = "event", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Integer saveEventFromManual(@RequestBody EventAdapter eventAdapter) {
        var responseObj = new ResponseObject();

        return eventService.saveEvent(
                eventAdapter.buildEvent(responseObj, eventService))
                .getEventId();
    }

    /**
     * Get event by using event id
     * @param eventId id of event
     * @return Event
     */
    @GetMapping(value = "event", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Event getEventById(@Param("eventId") Integer eventId) {

        return eventService.findEventByEventId(eventId).orElse(Event.builder().build());
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
    public ResponseObject updateEventInformation(@RequestBody EventAdapter eventAdapter) {
        Objects.requireNonNull(eventAdapter);
        var responseObj = new ResponseObject();
        pool.instantiationCampusLinkPrograms(campusLinkProgramService.loadCampusLinkPrograms());
        pool.instantiationSubSubjectTypes(subSubjectTypeService.loadAllSubSubjectTypes());
        pool.instantiationSuppliers(universityService.loadUniversity());

        if (eventAdapter.isChangeYear()) {
            eventService.findEventByEventId(eventAdapter.getEventId()).ifPresentOrElse(
                    e -> {
                        e.setEventStatus("Cancel");
                        e.setNote("Event cancelled by change planned start date to new year!");
                        eventAdapter.setUpdate(false);
                        eventAdapter.setEventId(null);
                        var event = eventAdapter.buildEvent(responseObj, eventService);
                         if (event != null) {
                             event.setCandidates(e.getCandidates());
                             eventService.saveEvent(e);
                             event.getCandidates().forEach(section -> { section.setEvent(event); section.setSectionId(null); });
                             eventService.saveEvent(event);
                             sectionService.saveAllSections(event.getCandidates());
                         }
                    }, () -> responseObj.addErrors("Update failed! Data might be not valid!")
            );
        } else {
            var event = eventAdapter.buildEvent(responseObj, eventService);
            eventService.saveEvent(event);
        }
        pool.destroy();

        return responseObj.setStatus();
    }
       
    @GetMapping(value = "events/recent", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Event> getRecentEvents(){
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        LocalDate firstDate = LocalDate.of(year, month,1);
        LocalDate lastDate = getFirstDateOfNextMonth(year, month);

        return eventService.getAllEventsInMonth(firstDate, lastDate);
    }

    @GetMapping(value = "events-month/{year}/{month}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Event> getALlEventsInMonth(@PathVariable("year") int year, @PathVariable("month") int month){
        LocalDate firstDate = LocalDate.of(year, month,1);
        LocalDate lastDate = getFirstDateOfNextMonth(year, month);

        return eventService.getAllEventsInMonth(firstDate, lastDate);
    }

    @GetMapping(value = "events-week/{start-date}/{end-date}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Event> getAllEventInWeek(@PathVariable("start-date") String startDate, @PathVariable("end-date") String endDate){
        LocalDate startLocalDate = parseStringToLocalDate(startDate);
        LocalDate endLocalDate = parseStringToLocalDate(endDate);

        return eventService.getAllEventsInWeek(startLocalDate, endLocalDate);
    }

    @GetMapping(value = "events-university/{university-code}/{start-date}/{end-date}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Event> getUniversityEventsInRange(@PathVariable("university-code") Integer university,@PathVariable("start-date") String startDate, @PathVariable("end-date") String endDate){
        LocalDate startLocalDate = parseStringToLocalDate(startDate);
        LocalDate endLocalDate = parseStringToLocalDate(endDate);

        return eventService.getEventInRangOfUniversity(startLocalDate, endLocalDate, university);
    }


    /**
     * The function is used to parse a String date to LocalDate
     * @param dateStr String date
     * @return LocalDate
     */
    private LocalDate parseStringToLocalDate(String dateStr){
        return LocalDate.parse(dateStr);
    }

    /**
     * Get the first date of next month.
     * @param year the year of date.
     * @param month the month of date.
     * @return the first date of month.
     */
    private LocalDate getFirstDateOfNextMonth(int year, int month){
        LocalDate lastDate;
        if(month != 12) {
            lastDate = LocalDate.of(year, month + 1, 1);
        }else{
            lastDate = LocalDate.of(year + 1, 1, 1);
        }
        return lastDate;
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

    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Autowired
    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }
}
