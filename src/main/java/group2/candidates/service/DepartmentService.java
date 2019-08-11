package group2.candidates.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group2.candidates.model.data.Department;
import group2.candidates.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private DepartmentRepository repository;

    /**
     * Use to find an university by using university name
     * @param universityName Name of university
     */
    public Optional<Department> findDepartmentByNameAndFacultyCode(String universityName, String facultyCode) {

        return repository.findDepartmentByNameAndFacultyCode(universityName, facultyCode);
    }

    /**
     * Use to save department
     * Formula: insert and update
     * @param department department to save
     * @return Department after saving
     */
    public Department saveDepartment(Department department) {

        return repository.saveAndFlush(department);
    }

    /**
     * Load all departments
     * @return Collection<Department>
     */
    public Collection<Department> loadAllDepartments() {
        
        return repository.findAll();
    }

    /**
     * @param repository the repository to set
     */
    @Autowired
    public void setRepository(DepartmentRepository repository) {
        this.repository = repository;
    }
}