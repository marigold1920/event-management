package group2.candidates.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    public University saveUniversity(University university) {

        return repository.saveAndFlush(university);
    }

    /**
     * Get all university name in system.
     * @return the list of university's name
     */
    public Collection<University> getAllUniversityName(){
        List<University> universityNameList = new ArrayList<>();
        List<University> universities = repository.findAll();
        for (University university: universities) {
            if(university.getUniversityName() != null) {
                universityNameList.add(university);
            }
        }
        return universityNameList;
    }

    /**
     * @param repository the repository to set
     */
    @Autowired
    public void setRepository(UniversityRepository repository) {
        this.repository = repository;
    }

    public Collection<University> loadUniversity() {

        return repository.findAll();
    }
}