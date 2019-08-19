package group2.candidates.adapter;

import group2.candidates.builder.EventBuilder;
import group2.candidates.common.ResponseObject;
import group2.candidates.model.data.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
public class EventAdapter {

    private Integer eventId;
    private String courseCode;
    private String courseName; //CAMPUS LINK
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
    public Event buildEvent(ResponseObject responseObject) {
        var builder = new EventBuilder()
                .event()
                    .information(eventId, courseCode, plannedExpense, budgetCode, subjectType, formatType, plannedStartDate, plannedEndDate,
                            actualStartDate, actualEndDate, actualLearningTime, actualExpense, trainingFeedback, trainingFeedbackContent,
                            trainingFeedbackTeacher, trainingFeedbackOrganization, note, eventStatus)
                .campusLinkProgram(courseName, responseObject)
                .supplier(supplier, responseObject)
                .subSubjectType(subSubjectType, responseObject);

        return builder.isValid() ? builder.build() : null;
    }
}
