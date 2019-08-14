package group2.candidates.service;

import group2.candidates.model.data.Section;
import group2.candidates.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SectionService {

    private SectionRepository repository;

    /**
     * Load all Sections of an Event by event id
     * @param eventId id of event
     * @param paginationIndex page number
     * @return Stream<Section>
     */
    public Collection<Section> loadSectionsOfAnEvent(Integer eventId, int paginationIndex) {

        return repository
                .loadSectionOfAnEvent(eventId, PageRequest.of(paginationIndex - 1, 10))
                .getContent();
    }

    /**
     * Save section to database
     * Use for Insert or Update section
     * @param section section
     */
    public Section saveSection(Section section) {

       return  repository.saveAndFlush(section);
    }

    Collection<Section> getAllSection(){
        return repository.findAll();
    }

    @Autowired
    public void setRepository(SectionRepository repository) {
        this.repository = repository;
    }
}
