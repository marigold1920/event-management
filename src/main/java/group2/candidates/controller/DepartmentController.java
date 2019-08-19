package group2.candidates.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group2.candidates.adapter.DepartmentAdapter;
import group2.candidates.model.data.Department;
import group2.candidates.service.DepartmentService;
import group2.candidates.service.FacultyService;
import group2.candidates.service.UniversityService;

@RestController
public class DepartmentController {

    private DepartmentService departmentService;
    private UniversityService universityService;
    private FacultyService facultyService;

    /**
     * Load all departments
     * @return Collection<Department>
     */
    @GetMapping(value = "departments", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Department> loadAllDepartments() {
        
        return departmentService.loadAllDepartments();
    }

    /**
     * Save all departments
     * @param departments collection of department after converting from json string
     * @return Collection<Department> after saving
     */
    @PostMapping(value = "departments", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Department> saveDepartments(@RequestBody Collection<DepartmentAdapter> departments) {

        return departments.stream()
                .map(d -> d.buildDepartment(universityService, facultyService))
                .map(departmentService::saveDepartment)
                .collect(Collectors.toList());
    }

    /**
     * @param departmentService the departmentService to set
     */
    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * @param facultyService the facultyService to set
     */
    @Autowired
    public void setFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    /**
     * @param universityService the universityService to set
     */
    @Autowired
    public void setUniversityService(UniversityService universityService) {
        this.universityService = universityService;
    }
}