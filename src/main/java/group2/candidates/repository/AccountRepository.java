package group2.candidates.repository;


import group2.candidates.model.data.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findAccountByUsername(String username);
}
