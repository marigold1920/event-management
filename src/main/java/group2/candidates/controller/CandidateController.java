package group2.candidates.controller;

import group2.candidates.model.data.Candidate;
import group2.candidates.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@RestController
public class CandidateController {

    private CandidateService candidateService;

    @GetMapping(value = "candidates/{paginationIndex}", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
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

    @PutMapping(value = "candidates", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public void updateCandidateInformation(@RequestBody Candidate candidate) {
        candidateService.saveCandidate(candidate);
    }

    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }
}
