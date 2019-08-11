package group2.candidates.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group2.candidates.model.data.CampusLinkProgram;
import group2.candidates.repository.CampusLinkProgramRepository;

@Service
public class CampusLinkProgramService  {

    private CampusLinkProgramRepository repository;

	public Optional<CampusLinkProgram> findProgram(String courseName) {

		return repository.findProgramByName(courseName);
	}

    /**
     * @param repository the repository to set
     */
    @Autowired
    public void setRepository(CampusLinkProgramRepository repository) {
        this.repository = repository;
    }
}