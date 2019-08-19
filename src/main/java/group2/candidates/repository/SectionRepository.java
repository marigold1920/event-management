package group2.candidates.repository;

import group2.candidates.model.data.Section;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SectionRepository extends JpaRepository<Section, Integer> {

    @Modifying
    @Query("update Section set contractType = ?1, candidateStatus = ?2, finalGrade = ?3, completionLevel = ?4, certificatedId = ?5, note = ?6 where sectionId = ?7")
    void updateTrainingInformation(String contractType, String candidateStatus, String finalGrade, String completionLevel, String certificatedId, String note, Integer sectionId);
}
