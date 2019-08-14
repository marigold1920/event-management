package group2.candidates.service;

import group2.candidates.model.data.Candidate;
import group2.candidates.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CandidateService {

    private CandidateRepository repository;

    /**
     * Load candidates from database and do pagination
     * Page size: 10
     * @param paginationIndex page number
     * @return Stream<Candidate>
     */
    public Collection<Candidate> loadCandidates(int paginationIndex) {

        return repository.findAll(PageRequest.of(paginationIndex - 1, 10)).getContent();
    }

    public Optional<Candidate> findCandidateById(Integer candidateId) {

        return repository.findById(candidateId);
    }

    /**
     * Save candidates model to database
     * Use for Insert or Update
     * @param candidate candidates for storing into database
     */
    public void saveCandidate(Candidate candidate) {
        repository.saveAndFlush(candidate);
    }

    /**
     * Delete candidates from database by using candidates id
     * @param candidateId id of candidates
     */
    public void deleteCandidate(int candidateId) {
        repository.findById(candidateId)
                .ifPresent(candidate -> repository.delete(candidate));
    }

    @Autowired
    public void setRepository(CandidateRepository repository) {
        this.repository = repository;
    }
}
