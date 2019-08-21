package group2.candidates.service;

import group2.candidates.model.data.Account;
import group2.candidates.model.data.Section;
import group2.candidates.model.data.SectionHistory;
import group2.candidates.repository.SectionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionHistoryService {
    private SectionHistoryRepository sectionHistoryRepository;


    public void saveSectionHistory(SectionHistory sectionHistory){
        sectionHistoryRepository.saveAndFlush(sectionHistory);
    }


    @Autowired
    public void setSectionHistoryRepository(SectionHistoryRepository sectionHistoryRepository) {
        this.sectionHistoryRepository = sectionHistoryRepository;
    }
}
