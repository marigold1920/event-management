package group2.candidates.controller;

import group2.candidates.model.data.Faculty;
import group2.candidates.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;


@RestController
public class FacultyController {

    private FacultyService facultyService;

    @GetMapping(value = "faculties", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Faculty> loadAllFaculties() {

        return facultyService.loadAllFaculties();
    }

    @PostMapping(value = "faculties", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Faculty> saveFaculties(@RequestBody Collection<Faculty> faculties) {

        return faculties.stream()
                .map(facultyService::saveFaculty)
                .collect(Collectors.toList());
    }

    @Autowired
    public void setFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
}
