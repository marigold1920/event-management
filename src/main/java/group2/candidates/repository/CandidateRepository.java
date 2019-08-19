package group2.candidates.repository;

import group2.candidates.model.data.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

    @Query("select c from Candidate c where c.email in :emails")
    Collection<Candidate> findAllByEmail(@Param("emails") Iterable<String> emails);

    @Query("select  c from Candidate  c where c.email = ?1")
   Candidate findCandidateByEmail(String email);
}
