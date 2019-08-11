package group2.candidates.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import group2.candidates.model.data.CampusLinkProgram;

@Transactional
public interface CampusLinkProgramRepository extends JpaRepository<CampusLinkProgram, String> {

    @Query("select p from CampusLinkProgram p where p.name = ?1")
	Optional<CampusLinkProgram> findProgramByName(String courseName);
    
}