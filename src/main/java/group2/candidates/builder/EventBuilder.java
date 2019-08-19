package group2.candidates.builder;

import group2.candidates.common.ResponseObject;
import group2.candidates.model.data.Event;
import group2.candidates.service.EventService;
import group2.candidates.tool.PoolService;
import lombok.Getter;
import lombok.Setter;

public class EventBuilder {

    Event event;
    PoolService pool = PoolService.getPoolService();
    @Setter @Getter private boolean valid = true;

    public EventInformationBuilder event() {
        return new EventInformationBuilder(event);
    }

    /**
     * Set supplier/partner  for event
     * @param supplier university name
     * @param responseObject  object for response to client
     * @return EventBuilder
     */
    public EventBuilder supplier(String supplier, ResponseObject responseObject) {
        pool.getSupplier(supplier).ifPresentOrElse(sup -> event.setSupplier(sup),
                () -> { responseObject.addErrors("System was not found University with name " + supplier); setValid(false); }
        );

        return this;
    }

    /**
     * Set Event type for event
     * @param subSubjectType event type name
     * @param responseObject  object for response to client
     * @return EventBuilder
     */
    public EventBuilder subSubjectType(String subSubjectType, ResponseObject responseObject) {
        pool.getSubSubjectType(subSubjectType).ifPresentOrElse(sub -> event.setSubSubjectType(sub),
                () -> { responseObject.addErrors("System was not found Event type with name " + subSubjectType); setValid(false);}
        );

        return this;
    }

    /**
     * Set Campus Link Program for event
     * @param campusLinkProgram campus link program name
     * @param responseObject  object for response to client
     * @return EventBuilder
     */
    public EventBuilder campusLinkProgram(String campusLinkProgram, ResponseObject responseObject) {
        pool.getCampusLinkProgram(campusLinkProgram).ifPresentOrElse(program -> event.setCampusLinkProgram(program),
                () ->  { responseObject.addErrors("System was not found Campus link program with name " + campusLinkProgram); setValid(false);}
        );

        return this;
    }

    /**
     * Set course code for event
     * @return EventBuilder
     */
    public EventBuilder courseCode(ResponseObject responseObject, EventService eventService, boolean update) {
        if (update) return this;

        var year  = event.getPlannedStartDate().getYear() % 100;
        var courseCode = String.join("_", event.getSupplier().getUniversityCode(), event.getCampusLinkProgram().getCode(),
                event.getSubSubjectType().getSubSubjectTypeName(), event.getSupplier().getSite() + year);

        if (eventService.checkCourseCodeOfEvent(courseCode, event.getPlannedStartDate(), event.getPlannedEndDate())) {
            responseObject.addErrors(String.format("Event type: %s,  Supplier: %s, SubSubjectType: %s, from: %s, to: %s had already existed in system!",
                    event.getCampusLinkProgram().getName(), event.getSupplier().getUniversityName(), event.getSubSubjectType().getSubSubjectTypeName(), event.getPlannedStartDate(), event.getPlannedEndDate()));
            setValid(false);
        } else {
            courseCode += String.format("_%03d", eventService.countEventOfYear(year) + 1);
            event.setCourseCode(courseCode);
        }

        return this;
    }

    public Event build() {
        return event;
    }
}
