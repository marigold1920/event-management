package group2.candidates.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import group2.candidates.model.data.Department;

@Transactional
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query("select d from Department d where d.university.universityName = ?1 and d.faculty.facultyCode = ?2")
	Optional<Department> findDepartmentByNameAndFacultyCode(String universityName, String facultyCode);

    @Query("select d from Department d where d.university.universityName = ?1 and d.faculty.name = ?2")
	Optional<Department> findDepartmentByNameAndFacultyName(String universityName, String facultyName);
}