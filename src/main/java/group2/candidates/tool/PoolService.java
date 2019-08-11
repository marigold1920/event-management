package group2.candidates.tool;

import java.util.HashMap;
import java.util.Map;

import group2.candidates.model.data.Department;
import group2.candidates.service.DepartmentService;

public class PoolService {
    public static PoolService poolService;
    private Map<String, Department> departments = new HashMap<>();

    private PoolService() {}

    public static synchronized PoolService getPoolService() {
        if (poolService == null) poolService = new PoolService();

        return poolService;
    }

    public Department getDepartment(String  universityName, String facultyCode, DepartmentService departmentService) {
        var key = universityName + facultyCode;
        
        if (!departments.containsKey(key)) {
            var department = departmentService.findDepartmentByNameAndFacultyCode(universityName, facultyCode).get();
            departments.put(key, department);
        }

        return departments.get(key);
    }

    public void destroy() {
        poolService = null;
    }
}