package group2.candidates.controller;

import group2.candidates.model.data.Section;
import group2.candidates.model.data.SectionHistory;
import group2.candidates.service.AccountService;
import group2.candidates.service.SectionHistoryService;
import group2.candidates.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

@RestController
public class SectionController {

    private SectionService sectionService;
    private SectionHistoryService sectionHistoryService;
    private AccountService accountService;

    @GetMapping(value = "sections/{paginationIndex}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Section> loadSections(@PathVariable int paginationIndex) {

        return sectionService.loadSections(paginationIndex);
    }

    @PutMapping(value = "sections", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Section updateTrainingInformationOfCandidate(@RequestBody Section section) {

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Section oldSection = sectionService.findSectionById(section.getSectionId());
        SectionHistory sectionHistory = new SectionHistory();
        sectionHistory.setSection(oldSection);
        sectionHistory.setContractType(oldSection.getContractType());
        sectionHistory.setUpdatedBy(username);
        sectionHistory.setUpdatedDate(LocalDate.now());
        sectionHistory.setCandidateStatus(oldSection.getCandidateStatus());
        sectionHistory.setCertificatedId(oldSection.getCertificatedId());
        sectionHistory.setCompletionLevel(oldSection.getCompletionLevel());
        sectionHistory.setNote(oldSection.getNote());
        sectionHistory.setFinalGrade(oldSection.getFinalGrade());
        sectionHistoryService.deleteSectionHistory(oldSection.getSectionHistory().getSectionHistoryId());
        sectionHistoryService.saveSectionHistory(sectionHistory);

        return sectionService.updateSection(section);
    }

    @PutMapping(value = "section", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Section deleteSection(@Param("sectionId") Integer sectionId) {

        return sectionService.deleteSection(sectionId);
    }

    @Autowired
    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @Autowired
    public void setSectionHistoryService(SectionHistoryService sectionHistoryService) {
        this.sectionHistoryService = sectionHistoryService;
    }
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
