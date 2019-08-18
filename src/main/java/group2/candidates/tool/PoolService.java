package group2.candidates.tool;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import group2.candidates.model.data.Candidate;
import group2.candidates.model.data.Department;
import group2.candidates.model.data.Event;
import group2.candidates.service.DepartmentService;

public class PoolService {
    private static PoolService poolService;
    private Map<String, Department> departments = new HashMap<>();
    private Map<String, Candidate> candidates;
    private Map<String, Event> events;

    private PoolService() {}

    public static synchronized PoolService getPoolService() {
        if (poolService == null) poolService = new PoolService();

        return poolService;
    }

    public void instantiationCandidates(Collection<Candidate> list) {
        candidates = list.stream()
                .collect(Collectors.toMap(Candidate::getEmail, Function.identity()));
    }

    public void instantiationEvents(Collection<Event> list) {
        events = list.stream()
                .collect(Collectors.toMap(Event::getCourseCode, Function.identity()));
    }

    public Optional<Candidate> getCandidate(String email) {

        return Optional.ofNullable(candidates.get(email));
    }

    public Optional<Event> getEvent(String courseCode) {

        return Optional.ofNullable(events.get(courseCode));
    }

    public Department getDepartment(String  universityName, String facultyCode, DepartmentService departmentService) {
        var key = universityName + facultyCode;
        
        if (!departments.containsKey(key)) {
            departmentService.findDepartmentByNameAndFacultyCode(universityName, facultyCode)
                    .ifPresentOrElse(department -> departments.put(key, department), () -> departments.put(key, null));
        }

        return departments.get(key);
    }

    public void destroy() {
        departments.clear();
        candidates.clear();
        events.clear();
    }
}