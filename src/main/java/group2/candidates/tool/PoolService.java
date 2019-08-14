package group2.candidates.tool;

import java.util.HashMap;
import java.util.Map;

import group2.candidates.model.data.Department;
import group2.candidates.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

public class PoolService {
    private static PoolService poolService;
    private static int order = 0;
    private Map<String, Department> departments = new HashMap<>();

    private PoolService() {}

    public static synchronized PoolService getPoolService() {
        if (poolService == null) poolService = new PoolService();

        return poolService;
    }

    public Department getDepartment(String  universityName, String facultyCode, DepartmentService departmentService) {
        var key = universityName + facultyCode;
        
        if (!departments.containsKey(key)) {
            var department = departmentService.findDepartmentByNameAndFacultyCode(universityName, facultyCode).orElseThrow();
            departments.put(key, department);
        }

        return departments.get(key);
    }

    public synchronized int getOrder() {

        return ++order;
    }

    public void destroy() {
        departments.clear();
    }
}