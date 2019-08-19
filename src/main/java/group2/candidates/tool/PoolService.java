package group2.candidates.tool;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import group2.candidates.model.data.*;
import group2.candidates.service.DepartmentService;

public final class PoolService {
    private static PoolService poolService;
    private Map<String, Department> departments = new HashMap<>();
    private Map<String, Candidate> candidates = new HashMap<>();
    private Map<String, Event> events  = new HashMap<>();
    private Map<String, University> suppliers = new HashMap<>();
    private Map<String, SubSubjectType> subSubjectTypes = new HashMap<>();
    private Map<String, CampusLinkProgram> campusLinkPrograms = new HashMap<>();

    private PoolService() {}

    /**
     * Get instantiation for PoolService, generate when poolService is null, otherwise,
     * return already instantiation
     * @return PoolService
     */
    public static synchronized PoolService getPoolService() {
        if (poolService == null) poolService = new PoolService();

        return poolService;
    }

    /**
     * Get all candidates from system matched with provided emails
     * Formula: use when add new section from excel file, useful when perform with
     * big data
     * @param list Collection of candidates matched with provided email
     */
    public void instantiationCandidates(Collection<Candidate> list) {
        candidates = list.stream()
                .collect(Collectors.toMap(Candidate::getEmail, Function.identity()));
    }

    /**
     * Get candidate from system matched with provided email
     * @param candidate candidate matched with provided email
     */
    public void instantiationCandidate(Candidate candidate) {
        candidates.put(candidate.getEmail(), candidate);
    }

    /**
     * Get all events from system matched with provided course codes
     * Formula: use when add new events from excel file, useful when perform with
     * big data
     * @param list Collection of events matched with provided course code
     */
    public void instantiationEvents(Collection<Event> list) {
        events = list.stream()
                .collect(Collectors.toMap(Event::getCourseCode, Function.identity()));
    }

    /**
     * Get all universities from system matched with provided university names
     * Formula: use when add new events from excel file, useful when perform with
     * big data
     * @param list Collection of universities matched with provided course code
     */
    public void instantiationSuppliers(Collection<University> list) {
        suppliers = list.stream()
                .collect(Collectors.toMap(University::getUniversityName, Function.identity()));
    }

    /**
     * Get all SubSubjectTypes from system matched with provided sub subject type names
     * Formula: use when add new events from excel file, useful when perform with
     * big data
     * @param list Collection of SubSubjectTypes matched with provided sub subject type names
     */
    public void instantiationSubSubjectTypes(Collection<SubSubjectType> list) {
        subSubjectTypes = list.stream()
                .collect(Collectors.toMap(SubSubjectType::getSubSubjectTypeName, Function.identity()));
    }

    /**
     * Get all CampusLinkPrograms from system matched with provided campus link program names
     * Formula: use when add new events from excel file, useful when perform with
     * big data
     * @param list Collection of CampusLinkPrograms matched with provided campus link program names
     */
    public void instantiationCampusLinkPrograms(Collection<CampusLinkProgram> list) {
        campusLinkPrograms = list.stream()
                .collect(Collectors.toMap(CampusLinkProgram::getName, Function.identity()));
    }

    /**
     * Get candidate by using email, use to check candidate has been quited,
     * check candidate has already joined in system base on course code
     * @param email email to get candidate
     * @return Optional<Candidate>
     */
    public Optional<Candidate> getCandidate(String email) {

        return Optional.ofNullable(candidates.get(email));
    }

    /**
     * Get event by using course code
     * @param courseCode course code of event
     * @return Optional<Event>
     */
    public Optional<Event> getEvent(String courseCode) {

        return Optional.ofNullable(events.get(courseCode));
    }

    /**
     * Get department by using university name and faculty code
     * Find department using in create new section
     * Data  will store until function in controller save all data
     * to system
     * @param universityName university name  for find university from University model
     * @param facultyCode faculty code for find faculty from Faculty model
     * @param departmentService get access to databasse
     * @return Department
     */
    public Department getDepartment(String  universityName, String facultyCode, DepartmentService departmentService) {
        var key = universityName + facultyCode;
        
        if (!departments.containsKey(key)) {
            departmentService.findDepartmentByNameAndFacultyCode(universityName, facultyCode)
                    .ifPresentOrElse(department -> departments.put(key, department), () -> departments.put(key, null));
        }

        return departments.get(key);
    }

    /**
     * Get supplier by using university name
     * @param supplier univsersity name
     * @return Optional<University>
     */
    public Optional<University> getSupplier(String supplier) {

        return Optional.ofNullable(suppliers.get(supplier));
    }

    /**
     * Get Sub Suject Type by using sub subject type name
     * @param subSubjectType sub subject type name
     * @return Optional<SubSubjectType>
     */
    public Optional<SubSubjectType> getSubSubjectType(String subSubjectType) {

        return Optional.ofNullable(subSubjectTypes.get(subSubjectType));
    }

    /**
     * Get CampusLinkProgram by using ampus link program name
     * @param campusLinkProgram campus link program name
     * @return Optional<CampusLinkProgram>
     */
    public Optional<CampusLinkProgram> getCampusLinkProgram(String campusLinkProgram) {

        return Optional.ofNullable(campusLinkPrograms.get(campusLinkProgram));
    }

    /**
     * Clear all data
     */
    public void destroy() {
        departments.clear();
        candidates.clear();
        events.clear();
        suppliers.clear();
        campusLinkPrograms.clear();
        subSubjectTypes.clear();
    }
}