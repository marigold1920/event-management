package group2.candidates.service;

import group2.candidates.model.data.Section;
import group2.candidates.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class SectionService {

    private SectionRepository repository;

    /**
     * Load all Sections of an Event by event id
     * @param paginationIndex page number
     * @return Stream<Section>
     */
    public Collection<Section> loadSections(int paginationIndex) {

        return repository.findAll(PageRequest.of(paginationIndex - 1, 10, Sort.Direction.DESC, "sectionId")).getContent();
    }

    /**
     * Save section to database
     * Use for Insert or Update section
     * @param section section
     */
    public Section saveSection(Section section) {

       return  repository.saveAndFlush(section);
    }

    /**
     * Update information of candidate when he/she joins in a event
     * @param section information for update, transfer as object
     * @return Section after  update
     */
    public Section updateSection(Section section) {
        repository.updateTrainingInformation(section.getContractType(), section.getCandidateStatus(),
                section.getFinalGrade(), section.getCompletionLevel(), section.getCertificatedId(), section.getNote(), section.getSectionId());

        return findSectionById(section.getSectionId());
    }

    /**
     * Find section by using id
     * @param sectionId id of section
     * @return Section
     */
    public Section findSectionById(Integer sectionId) {

        return repository.findById(sectionId).orElseThrow(IllegalArgumentException::new);
    }

    Collection<Section> getAllSection(){
        return repository.findAll();
    }

    public Section deleteSection(Integer sectionId) {
        var section = repository.findById(sectionId).orElseThrow(IllegalArgumentException::new);
        var status = section.getEvent().getEventStatus();
        if (status.equals("Cancel") || status.equals("Done")) throw new IllegalArgumentException();
        section.setCandidateStatus(status.equals("Planning") ? "Cancel" : "Drop-out");

        return saveSection(section);
    }

    public Collection<Section> getSectionByContractType(String contractType){
        return repository.getSectionByContractType(contractType);
    }


    @Autowired
    public void setRepository(SectionRepository repository) {
        this.repository = repository;
    }

    public void saveAllSections(Set<Section> candidates) {

        repository.saveAll(candidates);
    }
}
