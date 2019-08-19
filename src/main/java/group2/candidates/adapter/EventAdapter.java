package group2.candidates.adapter;

import group2.candidates.builder.EventBuilder;
import group2.candidates.common.ResponseObject;
import group2.candidates.model.data.Event;
import group2.candidates.service.EventService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
public class EventAdapter {

    private Integer eventId;
    private String courseName; //CAMPUS LINK
    private String courseCode;
    private String subjectType;
    private String subSubjectType; //SUB SUBJECT TYPE OBJECT
    private String plannedExpense;
    private String formatType; //ENUM
    private String supplier; //LINK TO UNIVERSITY
    private String budgetCode;
    private LocalDate plannedStartDate;
    private LocalDate plannedEndDate;
    private LocalDate actualStartDate;
    private LocalDate actualEndDate;
   private int actualLearningTime;
    private String actualExpense;
    private String trainingFeedback;
    private String trainingFeedbackContent;
    private String trainingFeedbackTeacher;
    private String trainingFeedbackOrganization;
    private String updatedBy; //Admin Account  ID
    private LocalDate updatedDate; //TIME UPDATE
    private String eventStatus;
    private String note;

    /**
     * Serializable Json process: Json Object to EventAdapter --> Event
     * This function will build Event with SIMPLE information
     * Use for create Event WITHOUT candidates data list
     * Call after creating EventAdapter
     * @return Event
     */
    public Event buildEvent(ResponseObject responseObject, EventService eventService) {
        var builder = new EventBuilder()
                .event()
                    .information(eventId, plannedExpense, budgetCode, subjectType, formatType, plannedStartDate, plannedEndDate,
                            actualStartDate, actualEndDate, actualLearningTime, actualExpense, trainingFeedback, trainingFeedbackContent,
                            trainingFeedbackTeacher, trainingFeedbackOrganization, note, eventStatus)
                .campusLinkProgram(courseName, responseObject)
                .supplier(supplier, responseObject)
                .subSubjectType(subSubjectType, responseObject)
                .courseCode(responseObject, eventService);

        return builder.isValid() ? builder.build() : null;
    }
}
