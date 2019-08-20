package group2.candidates.tool;

import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import group2.candidates.model.data.Event;

public class EventJsonSerializable implements JsonSerializer<Event> {

	@Override
	public JsonElement serialize(Event event, Type type, JsonSerializationContext context) {
        JsonObject jsonObj = new JsonObject();
        
        jsonObj.addProperty("eventId", event.getEventId());

        var campusLinkProgram = event.getCampusLinkProgram();
        if (campusLinkProgram != null) {
                jsonObj.addProperty("courseName", campusLinkProgram.getName());
                jsonObj.addProperty("plannedLearningTime", campusLinkProgram.getLearningTime());
        }

        var subSubjectType = event.getSubSubjectType();
        if (subSubjectType != null) 
            jsonObj.addProperty("subSubjectType", subSubjectType.getSubSubjectTypeName());

        jsonObj.addProperty("courseCode", event.getCourseCode());
        jsonObj.addProperty("budgetCode", event.getBudgetCode());
        jsonObj.addProperty("subjectType", event.getSubjectType());
        jsonObj.addProperty("formatType", event.getFormatType());

        var supplier = event.getSupplier();
        if (supplier != null) {
            jsonObj.addProperty("site", supplier.getSite());
            jsonObj.addProperty("supplier", supplier.getUniversityName());
        }

        jsonObj.addProperty("plannedStartDate", event.getPlannedStartDate().format(DateTimeFormatter.ofPattern("d-MMM-yyyy")));
        jsonObj.addProperty("plannedEndDate", event.getPlannedEndDate().format(DateTimeFormatter.ofPattern("d-MMM-yyyy")));
        jsonObj.addProperty("plannedExpense", event.getPlannedExpense());

        var candidates = event.getCandidates();
        if (candidates != null && !candidates.isEmpty())
            jsonObj.addProperty("plannedNumberOfStudents", candidates.size());
        var from = event.getActualStartDate();
        if (from != null) {
                jsonObj.addProperty("actualStartDate", from.format(DateTimeFormatter.ofPattern("d-MMM-yyyy")));
        }
        var to =  event.getActualEndDate();
        if (to  != null) {
                jsonObj.addProperty("actualEndDate", to.format(DateTimeFormatter.ofPattern("d-MMM-yyyy")));
        }
//        jsonObj.addProperty("actualStartDate", event.getActualStartDate().format(DateTimeFormatter.ofPattern("d-MMM-yyyy")));
//        jsonObj.addProperty("actualEndDate", event.getActualEndDate().format(DateTimeFormatter.ofPattern("d-MMM-yyyy")));
        jsonObj.addProperty("actualLearningTime", event.getActualLearningTime());
        jsonObj.addProperty("actualNumberOfTrainees", event.getActualNumberOfTrainees());
        jsonObj.addProperty("actualNumberOfEnrolled", event.getActualNumberOfEnrolled());
        jsonObj.addProperty("numberOfCandidateCitified", event.getNumberOfCandidateCitified());
        jsonObj.addProperty("actualExpense", event.getActualExpense());
        jsonObj.addProperty("trainingFeedback", event.getTrainingFeedback());
        jsonObj.addProperty("trainingFeedbackTeacher", event.getTrainingFeedbackTeacher());
        jsonObj.addProperty("trainingFeedbackContent", event.getTrainingFeedbackContent());
        jsonObj.addProperty("trainingFeedbackOrganization", event.getTrainingFeedbackOrganization());
        jsonObj.addProperty("note", event.getNote());
        jsonObj.addProperty("eventStatus", event.getEventStatus());
        jsonObj.addProperty("isChosen", false);
        jsonObj.addProperty("changeYear", false);
        jsonObj.addProperty("update", false);

		return jsonObj;
	}
}