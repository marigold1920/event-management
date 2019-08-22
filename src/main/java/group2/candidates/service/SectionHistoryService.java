package group2.candidates.service;

import group2.candidates.model.data.Account;
import group2.candidates.model.data.Section;
import group2.candidates.model.data.SectionHistory;
import group2.candidates.repository.SectionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SectionHistoryService {
    private SectionHistoryRepository sectionHistoryRepository;


    public void saveSectionHistory(SectionHistory sectionHistory){
        sectionHistoryRepository.saveAndFlush(sectionHistory);
    }

    public void deleteSectionHistory(Integer sectionId){
        if(sectionHistoryRepository.findById(sectionId).isPresent()){
            sectionHistoryRepository.deleteById(sectionId);
            System.out.println("Success");
        }
    }

    @Autowired
    public void setSectionHistoryRepository(SectionHistoryRepository sectionHistoryRepository) {
        this.sectionHistoryRepository = sectionHistoryRepository;
    }
}
