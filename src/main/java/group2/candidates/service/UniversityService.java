package group2.candidates.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group2.candidates.model.data.University;
import group2.candidates.repository.UniversityRepository;

@Service
public class UniversityService {

    private UniversityRepository repository;

    /**
     * Find University by using university name
     * @param universityName
     * @return Optional<University> wrapper null value when no university found
     */
    public Optional<University> findUniversityByName(String universityName) {

        return repository.findUniversityByName(universityName);
    }

    /**
     * 
     * @param university
     */
    public void saveUniversity(University university) {
        repository.saveAndFlush(university);
    }

    /**
     * @param repository the repository to set
     */
    @Autowired
    public void setRepository(UniversityRepository repository) {
        this.repository = repository;
    }
}