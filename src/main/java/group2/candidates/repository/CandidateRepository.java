package group2.candidates.repository;

import group2.candidates.model.data.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

}
