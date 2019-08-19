package group2.candidates.builder;

import group2.candidates.model.data.Event;

import java.time.LocalDate;

public class EventInformationBuilder extends EventBuilder {

    EventInformationBuilder(Event event) {
        this.event = event;
    }

    public EventInformationBuilder information(Integer eventId, String plannedExpense, String budgetCode, String subjectType, String formatType, LocalDate plannedStartDate,
                                               LocalDate plannedEndDate, LocalDate actualStartDate, LocalDate actualEndDate, Integer actualLearningTime, String actualExpense, String trainingFeedback,
                                               String trainingFeedbackContent, String trainingFeedbackTeacher, String trainingFeedbackOrganization, String note, String eventStatus) {
        event = Event.builder()
                .eventId(eventId)
                .plannedExpense(plannedExpense)
                .budgetCode(budgetCode)
                .subjectType(subjectType)
                .formatType(formatType)
                .plannedStartDate(plannedStartDate)
                .plannedEndDate(plannedEndDate)
                .actualStartDate(actualStartDate)
                .actualEndDate(actualEndDate)
                .actualLearningTime(actualLearningTime)
                .actualExpense(actualExpense)
                .trainingFeedback(trainingFeedback)
                .trainingFeedbackContent(trainingFeedbackContent)
                .trainingFeedbackTeacher(trainingFeedbackTeacher)
                .trainingFeedbackOrganization(trainingFeedbackOrganization)
                .note(note)
                .eventStatus(eventStatus == null ? "" : eventStatus)
                .build();

        return this;
    }
}
