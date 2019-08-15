package group2.candidates.service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
     * get list all campus link program.
     * @return the list of campus link program.
     */
	Collection<CampusLinkProgram> getAllCampusLinkProgram(){
	    return repository.findAll();
    }

    public CampusLinkProgram saveCampusLinkProgram(CampusLinkProgram campusLinkProgram) {

        return repository.saveAndFlush(campusLinkProgram);
    }

    /**
     * @param repository the repository to set
     */
    @Autowired
    public void setRepository(CampusLinkProgramRepository repository) {
        this.repository = repository;
    }

    public Collection<CampusLinkProgram> loadCampusLinkPrograms() {

        return repository.findAll();
    }
}
