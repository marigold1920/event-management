package group2.candidates.tool;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import group2.candidates.model.data.Section;

import java.lang.reflect.Type;

public class SectionJsonSerializable implements JsonSerializer<Section> {

	@Override
	public JsonElement serialize(Section section, Type type, JsonSerializationContext context) {
        JsonObject jsonObj = new JsonObject();

        jsonObj.addProperty("eventId", section.getSectionId().getEventId());
        jsonObj.addProperty("contractType", section.getContractType());
        jsonObj.addProperty("candidateStatus", section.getCandidateStatus());
        jsonObj.addProperty("finalGrade", section.getFinalGrade());
        jsonObj.addProperty("completionLevel", section.getCompletionLevel());
        jsonObj.addProperty("certificatedId", section.getCertificatedId());
        jsonObj.addProperty("note", section.getNote());

        var candidate = section.getCandidate();
        if (candidate != null) {
            jsonObj.addProperty("candidateId", candidate.getCandidateId());
            jsonObj.addProperty("candidateName", candidate.getName());
            jsonObj.addProperty("account", candidate.getAccount());
            jsonObj.addProperty("university", candidate.getUniversity().getUniversity().getUniversityName());
            jsonObj.addProperty("gender", candidate.getGender());
        }

        return jsonObj;
	}
}