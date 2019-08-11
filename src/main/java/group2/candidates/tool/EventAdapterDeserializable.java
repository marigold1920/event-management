package group2.candidates.tool;

import com.google.gson.*;
import group2.candidates.adapter.EventAdapter;

import java.lang.reflect.Type;

public class EventAdapterDeserializable implements JsonDeserializer<EventAdapter> {
    @Override
    public EventAdapter deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObj = json.getAsJsonObject();
        JsonElement element;
        boolean isExcel = jsonObj.has("Course Code");

        return EventAdapter.builder()
                    .eventId((element = jsonObj.get("eventId")) == null ? 0 : element.getAsInt())
                    .courseCode((element = jsonObj.get(isExcel ? "Course Code" : "courseCode")) == null ? null : element.getAsString())
                    .courseName((element = jsonObj.get(isExcel ? "Course Name": "courseName")) == null ? null : element.getAsString())
                    .supplier((element = jsonObj.get(isExcel ? "Supplier/Partner" : "supplier")) == null ? null : element.getAsString())
                    .subjectType((element = jsonObj.get(isExcel ? "Subject Type" : "subjectType")) == null ? null : element.getAsString())
                    .subSubjectType((element = jsonObj.get(isExcel ? "Sub-Subject Type" : "subSubjectType")) == null ? null : element.getAsString())
                    .budgetCode((element = jsonObj.get(isExcel ? "Budget Code" : "budgetCode")) == null ? null : element.getAsString())
                    .formatType((element = jsonObj.get(isExcel ? "Format Type" : "formatType")) == null ? null : element.getAsString())
                    .plannedStartDate((element = jsonObj.get(isExcel ? "Planned Start date" : "plannedStartDate")) == null ? null : LocalDateConverter.serializable(element))
                    .plannedEndDate((element = jsonObj.get(isExcel ? "Planned End Date" : "plannedEndDate")) == null ? null : LocalDateConverter.serializable(element))
                    .plannedExpense((element = jsonObj.get(isExcel ? "Planned Expense" : "plannedExpense")) == null ? null : element.getAsString())
                    .actualStartDate((element = jsonObj.get(isExcel ? "Actual Start Date" : "actualStartDate")) == null ? null : LocalDateConverter.serializable(element))
                    .actualEndDate((element = jsonObj.get(isExcel ? "Actual End Date" : "actualEndDate")) == null ? null : LocalDateConverter.serializable(element))
                    .actualLearningTime((element = jsonObj.get(isExcel ? "Actual Learning Time\r\n(hour)" : "actualLearningTime")) == null ? 0 : element.getAsInt())
                    .actualExpense((element = jsonObj.get(isExcel ? "Actual Expense" : "actualExpense")) == null ? null : element.getAsString())
                    .trainingFeedback((element = jsonObj.get(isExcel ? "Training feedback" : "trainingFeedback")) == null ? null : element.getAsString())
                    .trainingFeedbackTeacher((element = jsonObj.get(isExcel ? "Training feedback - Teacher" : "trainingFeedbackTeacher")) == null ? null : element.getAsString())
                    .trainingFeedbackContent((element = jsonObj.get(isExcel ? "Training feedback - Content" : "trainingFeedbackContent")) == null ? null : element.getAsString())
                    .trainingFeedbackOrganization((element = jsonObj.get(isExcel ? "Training feedback - Organization" : "trainingFeedbackOrganization")) == null ? null : element.getAsString())
                    .eventStatus((element = jsonObj.get(isExcel ? "Course status" : "eventStatus")) == null ? null : element.getAsString())
                    .note((element = jsonObj.get(isExcel ? "Note" : "note")) == null ? null : element.getAsString())
                .build();
    }
}
