package group2.candidates.repository;

import group2.candidates.model.data.SectionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionHistoryRepository extends JpaRepository<SectionHistory, Integer> {

}
