package group2.candidates.repository;

import group2.candidates.model.data.SubSubjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubSubjectTypeRepository extends JpaRepository<SubSubjectType, String> {

    @Query("select s from SubSubjectType s where s.subSubjectTypeName = ?1")
    Optional<SubSubjectType> findSubSubjectTypeByName(String subSubjectTypeName);

}
