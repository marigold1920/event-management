package group2.candidates.adapter;

import group2.candidates.model.data.Event;
import group2.candidates.service.CampusLinkProgramService;
import group2.candidates.service.SubSubjectTypeService;
import group2.candidates.service.UniversityService;
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
    public Event buildEvent(SubSubjectTypeService subSubjectTypeService, UniversityService universityService, 
                                                                CampusLinkProgramService  campusLinkProgramService) {
        var sub = subSubjectTypeService.findSubSubjectTypeByName(subSubjectType).orElseThrow();
        var university = universityService.findUniversityByName(supplier).orElseThrow();
        var campusLinkProgram = campusLinkProgramService.findProgram(courseName).orElseThrow();

        return Event.builder()
                    .eventId(eventId)
                    .courseCode(courseCode)
                    .campusLinkProgram(campusLinkProgram)
                    .plannedExpense(plannedExpense)
                    .budgetCode(budgetCode)
                    .subjectType(subjectType)
                    .subSubjectType(sub)
                    .formatType(formatType)
                    .supplier(university)
                    .plannedStartDate(plannedStartDate)
                    .plannedEndDate(plannedEndDate)
                    .actualStartDate(actualStartDate)
                    .actualEndDate(actualEndDate)
                    .actualLearningTime(actualLearningTime)
                    .actualExpense(actualExpense)
                    .trainingFeedback(trainingFeedback)
                    .trainingFeedbackTeacher(trainingFeedbackTeacher)
                    .trainingFeedbackOrganization(trainingFeedbackOrganization)
                    .eventStatus(eventStatus == null ? "" : eventStatus)
                .build();
    }
}
