package group2.candidates.adapter;

import group2.candidates.model.data.Department;
import group2.candidates.service.FacultyService;
import group2.candidates.service.UniversityService;
import lombok.Data;

@Data
public class DepartmentAdapter {

    private String facultyName;
    private String universityName;
    private String cooperationStartDate;
    private int level;

    /**
     * Build department
     * Formula: set all primitive attributes, reference to University,
     * reference to Faculty
     * @param universityService service for finding university
     * @param facultyService service for finding faculty
     * @return Department
     */
    public Department buildDepartment(UniversityService universityService, FacultyService facultyService) {
        var university = universityService.findUniversityByName(universityName).orElseThrow();
        var faculty = facultyService.findFacultyByName(facultyName).orElseThrow();

        return Department.builder()
                        .faculty(faculty)
                        .university(university)
                        .cooperationStartDate(cooperationStartDate)
                        .level(level)
                    .build();
    }
}