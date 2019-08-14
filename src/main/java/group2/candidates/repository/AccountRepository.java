package group2.candidates.repository;


import group2.candidates.model.data.Account;
import group2.candidates.model.data.CampusLinkProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    @Query("select p from Account p where p.username = ?1")
    Optional<Account> findAccountByUsername(String username);
}
