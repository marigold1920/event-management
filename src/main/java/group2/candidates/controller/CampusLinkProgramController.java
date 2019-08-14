package group2.candidates.controller;

import group2.candidates.model.data.CampusLinkProgram;
import group2.candidates.service.CampusLinkProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class CampusLinkProgramController {

    private CampusLinkProgramService campusLinkProgramService;

    @GetMapping(value = "programs", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Collection<CampusLinkProgram> loadCampusLinkPrograms() {

        return campusLinkProgramService.loadCampusLinkPrograms();
    }

    @PostMapping(value = "programs", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Collection<CampusLinkProgram> saveCampusLinkPrograms(@RequestBody Collection<CampusLinkProgram> campusLinkPrograms) {

        return campusLinkPrograms.parallelStream()
                        .map(campusLinkProgramService::saveCampusLinkProgram)
                        .collect(Collectors.toList());
    }

    @GetMapping(value = "programs-name", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Collection<String> getAllCoursesName(){
        return campusLinkProgramService.getAllCourseName();
    }

    @Autowired
    public void setCampusLinkProgramService(CampusLinkProgramService campusLinkProgramService) {
        this.campusLinkProgramService = campusLinkProgramService;
    }
}
