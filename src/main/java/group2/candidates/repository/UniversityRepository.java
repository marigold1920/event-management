package group2.candidates.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import group2.candidates.model.data.University;

@Transactional
public interface UniversityRepository extends JpaRepository<University, Integer> {
    
    @Query("select u from University u where u.universityName = ?1")
	Optional<University> findUniversityByName(String universityName);
}