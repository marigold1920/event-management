package group2.candidates.tool;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import group2.candidates.model.data.Department;

public class DepartmentJsonSerializable implements JsonSerializer<Department> {

	@Override
	public JsonElement serialize(Department department, Type type, JsonSerializationContext context) {
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("departmentId", department.getDepartmentId());
        jsonObj.addProperty("universityCode", department.getUniversity().getUniversityCode());
        jsonObj.addProperty("universityName", department.getUniversity().getUniversityName());
        jsonObj.addProperty("facultyCode", department.getFaculty().getFacultyCode());
        jsonObj.addProperty("facultyName", department.getFaculty().getName());
        jsonObj.addProperty("cooperationStartDate", department.getCooperationStartDate());
        jsonObj.addProperty("level", department.getLevel());

        return jsonObj;
	}
}