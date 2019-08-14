package group2.candidates.tool;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import group2.candidates.model.data.Section;

public class SectionJsonDeserializable implements JsonDeserializer<Section> {

	@Override
	public Section deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
        var jsonObj = json.getAsJsonObject();
        JsonElement jsonElement;

         return Section.builder()
                                        .sectionId((jsonElement = jsonObj.get("sectionId")) == null ? null : jsonElement.getAsInt())
                                        .contractType((jsonElement = jsonObj.get("contractType")) == null ? null : jsonElement.getAsString())
                                        .candidateStatus((jsonElement = jsonObj.get("candidateStatus")) == null ? null : jsonElement.getAsString())
                                        .finalGrade((jsonElement = jsonObj.get("finalGrade")) == null ? null : jsonElement.getAsString())
                                        .completionLevel((jsonElement = jsonObj.get("completionLevel")) == null ? null : jsonElement.getAsString())
                                        .note((jsonElement = jsonObj.get("note")) == null ? null : jsonElement.getAsString())
                                    .build();
	}
}