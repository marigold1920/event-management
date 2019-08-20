package group2.candidates.tool;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import group2.candidates.model.data.Candidate;

public class CandidateJsonSerializable implements JsonSerializer<Candidate> {

	@Override
	public JsonElement serialize(Candidate  candidate, Type type, JsonSerializationContext context) {
        JsonObject jsonObj = new JsonObject();

        jsonObj.addProperty("candidateId", candidate.getCandidateId());
        jsonObj.addProperty("universityName", candidate.getUniversity().getUniversity().getUniversityName());
        jsonObj.addProperty("facultyName", candidate.getUniversity().getFaculty().getName());
        jsonObj.addProperty("account", candidate.getAccount());
        jsonObj.addProperty("email", candidate.getEmail());
        jsonObj.addProperty("nationalId", candidate.getNationalId());
        jsonObj.addProperty("name", candidate.getName());
        var dob = candidate.getDayOfBirth();
        if (dob != null)
                jsonObj.addProperty("dayOfBirth", dob.toString());
        jsonObj.addProperty("gender", candidate.getGender());
        jsonObj.addProperty("phone", candidate.getPhone());
        jsonObj.addProperty("graduationDate", candidate.getGraduationDate());
        var fullTimeWorking = candidate.getFullTimeWorking();
        if (fullTimeWorking != null) {
            jsonObj.addProperty("fullTimeWorking", fullTimeWorking.toString());
        }
        jsonObj.addProperty("skill", candidate.getSkill());
        jsonObj.addProperty("gpa", candidate.getGpa());

        return jsonObj;
	}
}