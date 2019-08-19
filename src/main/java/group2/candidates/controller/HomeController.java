package group2.candidates.controller;

import group2.candidates.model.data.CampusLinkProgram;
import group2.candidates.service.CampusLinkProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class HomeController {
    private CampusLinkProgramService campusLinkProgramService;

    @Autowired
    public void setCampusLinkProgramService(CampusLinkProgramService campusLinkProgramService) {
        this.campusLinkProgramService = campusLinkProgramService;
    }

    @GetMapping("/")
    public Collection<CampusLinkProgram> showHome() {
        return campusLinkProgramService.loadCampusLinkPrograms();
    }
}
