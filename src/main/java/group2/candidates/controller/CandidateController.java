package group2.candidates.controller;

import group2.candidates.common.ResponseObject;
import group2.candidates.model.data.Candidate;
import group2.candidates.service.CandidateService;
import group2.candidates.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class CandidateController {

    private CandidateService candidateService;
    private DepartmentService departmentService;

    @PutMapping(value = "candidates", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public ResponseObject updateCandidateInformation(@RequestBody Candidate candidate) {
        var responseObj = new ResponseObject();

        candidateService.findCandidateById(candidate.getCandidateId()).ifPresent(c -> {
            candidate.setEvents(c.getEvents());
            candidate.getEvents().forEach(section -> section.setCandidate(candidate));
            candidate.setCandidateId(Objects.hash(candidate.getName(), candidate.getEmail()));
            departmentService.findDepartmentByNameAndFacultyName(
                    candidate.getUniversityName(), candidate.getFacultyName()
            ).ifPresentOrElse(candidate::setUniversity, () -> responseObj.addErrors("System was not found " + candidate.getUniversity() + " with " + candidate.getFacultyName() + "!"));
            candidateService.deleteCandidate(c.getCandidateId());
            candidateService.saveCandidate(candidate);
        });

        return responseObj.setStatus();
    }

    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
}
