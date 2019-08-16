package group2.candidates.controller;

import group2.candidates.model.data.Section;
import group2.candidates.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class SectionController {

    private SectionService sectionService;

    @GetMapping(value = "sections/{paginationIndex}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Section> loadSections(@Param("eventId") Integer eventId, @PathVariable int paginationIndex) {

        return sectionService.loadSectionsOfAnEvent(eventId, paginationIndex);
    }

    @PutMapping(value = "sections", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Section updateTrainingInformationOfCandidate(@RequestBody Section section) {

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
}
