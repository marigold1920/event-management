package group2.candidates.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group2.candidates.model.data.Faculty;
import group2.candidates.repository.FacultyRepository;

@Service
public class FacultyService {

    private FacultyRepository repository;

    public Collection<Faculty> loadAllFaculties() {

        return repository.findAll();
    }

    public Faculty saveFaculty(Faculty faculty) {

        return repository.saveAndFlush(faculty);
    }

    public Optional<Faculty> findFacultyByName(String facultyName) {

        return repository.findFacultyByName(facultyName);
    }

    @Autowired
    public void setRepository(FacultyRepository repository) {
        this.repository = repository;
    }
}