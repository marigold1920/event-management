package group2.candidates.controller;

import group2.candidates.model.data.University;
import group2.candidates.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class UniversityController {

    private UniversityService universityService;

    @GetMapping(value = "universities", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<University> loadUniversities() {

        return universityService.loadUniversity();
    }

    @PostMapping(value = "universities", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Integer> saveUniversities(@RequestBody Collection<University> universities) {

        return universities.parallelStream()
            .map(universityService::saveUniversity)
            .map(University::getUniversityId)
            .collect(Collectors.toList());
    }

    @GetMapping(value = "universities-name", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<String> getAllUniversityName(){
        return universityService.getAllUniversityName();
    }

    @Autowired
    public void setUniversityService(UniversityService universityService) {
        this.universityService = universityService;
    }
}
