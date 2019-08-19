package group2.candidates.controller;

import group2.candidates.model.data.Candidate;
import group2.candidates.service.CandidateService;
import group2.candidates.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@RestController
public class CandidateController {

    private CandidateService candidateService;
    private DepartmentService departmentService;

    @GetMapping(value = "candidates/{paginationIndex}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Candidate> loadCandidates(@PathVariable int paginationIndex) {
        if (paginationIndex < 1) return new ArrayList<>();

        return candidateService.loadCandidates(paginationIndex);
    }

    @PostMapping(value = "candidates", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public void saveCandidatesFromExcelFile(@RequestBody Collection<Candidate> candidates) {
        candidates.forEach(candidateService::saveCandidate);
    }

    @DeleteMapping(value = "candidates")
    public void deleteCandidates(@Param("candidateId") Integer[] candidateId) {
        if (candidateId == null) return;

        Arrays.asList(candidateId).forEach(candidateService::deleteCandidate);
    }

//    @PutMapping(value = "candidates", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
//    public void updateCandidateInformation(@RequestBody Candidate candidate) {
//        candidateService.findCandidateById(candidate.getCandidateId()).ifPresent(c -> {
//            candidate.setEvents(c.getEvents());
//            candidate.getEvents().forEach(section -> section.setCandidate(candidate));
//            candidate.setCandidateId(Objects.hash(candidate.getName(), candidate.getEmail()));
//            candidate.setUniversity(departmentService.findDepartmentByNameAndFacultyName(
//                    candidate.getUniversityName(), candidate.getFacultyName()
//            ).orElseThrow(() -> new IllegalArgumentException("Can't be found University base on provided Information")));
//            candidateService.deleteCandidate(c.getCandidateId());
//            candidateService.saveCandidate(candidate);
//        });
//    }

    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
}
