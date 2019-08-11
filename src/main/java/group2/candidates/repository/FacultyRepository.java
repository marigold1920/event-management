package group2.candidates.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import group2.candidates.model.data.Faculty;

@Transactional
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    @Query("select f from Faculty f where f.name = ?1")
	Optional<Faculty> findFacultyByName(String facultyName);
}