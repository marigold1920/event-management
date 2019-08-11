package group2.candidates.tool;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import group2.candidates.adapter.SectionAdapter;

import java.lang.reflect.Type;

public class SectionAdapterJsonDeserializable implements JsonDeserializer<SectionAdapter> {
    @Override
    public SectionAdapter deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        var jsonObj = json.getAsJsonObject();
        JsonElement element;
        boolean isExcel = jsonObj.has("Name");

        return SectionAdapter.builder()
                    .pool(PoolService.getPoolService())
                    .nationalId((element = jsonObj.get(isExcel ? "National ID" : "nationalId")) == null ? null : element.getAsString())
                    .account((element = jsonObj.get(isExcel ? "Account" : "account")) == null ? null : element.getAsString())
                    .name((element = jsonObj.get(isExcel ? "Name" : "name")) == null ? null : element.getAsString())
                    .universityName((element = jsonObj.get(isExcel ? "University" : "universityName")) == null ? null : element.getAsString())
                    .facultyCode((element = jsonObj.get(isExcel ? "Faculty" : "facultyCode")) == null ? null : element.getAsString())
                    .dob((element = jsonObj.get(isExcel ? "DOB" : "dob")) == null ? null : element.getAsString())
                    .gender((element = jsonObj.get(isExcel ? "Gender" : "gender")) == null ? null : element.getAsString())
                    .email((element = jsonObj.get(isExcel ? "E-mail" : "email")) == null ? null : element.getAsString())
                    .phone((element = jsonObj.get(isExcel ? "Phone" : "phone")) == null ? null : element.getAsString())
                    .facebook((element = jsonObj.get(isExcel ? "Facebook" : "facebook")) == null ? null : element.getAsString())
                    .universityGraduationDate((element = jsonObj.get(isExcel ? "University graduation date" : "universityGraduationDate")) == null ? null : element.getAsInt())
                    .fullTimeWorking((element = jsonObj.get(isExcel ? "Full-time working available date" : "fullTimeWorking")) == null ? null : LocalDateConverter.serializable(element))
                    .gpa((element = jsonObj.get(isExcel ? "GPA" : "gpa")) == null ? 0.0 : element.getAsDouble())
                    .courseCode((element = jsonObj.get(isExcel ? "Course code" : "courseCode")) == null ? null : element.getAsString())
                    .status((element = jsonObj.get(isExcel ? "Status" : "status")) == null ? null : element.getAsString())
                    .finalGrade((element = jsonObj.get(isExcel ? "Final grade" : "finalGrade")) == null ? null : element.getAsString())
                    .completionLevel((element = jsonObj.get(isExcel ? "Completion level" : "completionLevel")) == null ? null : element.getAsString())
                    .certificateId((element = jsonObj.get(isExcel ? "Certificate ID" : "certificateId")) == null ? null : element.getAsString())
                    .updatedBy((element = jsonObj.get(isExcel ? "Updated by" : "updatedBy")) == null ? null : element.getAsString())
                    .note((element = jsonObj.get(isExcel ? "Note" : "note")) == null ? null : element.getAsString())
                    .contractType((element = jsonObj.get(isExcel ? "1st contract Type" : "contractType")) == null ? null : element.getAsString())
                .build();
    }
}
