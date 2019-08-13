package group2.candidates.service;

import group2.candidates.model.data.Account;
import group2.candidates.model.data.Authority;
import group2.candidates.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    // private AuthorityRepository authorityRepository;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    public List<Account> loadAllAccounts(){
        return accountRepository.findAll();
    }


    /**
     * Add new account to system.
     * @param account the new account.
     * @return added account if enable success, otherwise return null.
     */
    public Account addAccount(Account account){
        if(!accountRepository.findById(account.getUsername()).isPresent()){
            return accountRepository.saveAndFlush(account);
        }else{
            return null;
        }
    }


    /**
     * Update a account.
     * @param account the new account
     * @return updated account if update success, otherwise return null.
     */
    public Account updateAccount(Account account){
        if(accountRepository.findById(account.getUsername()).isPresent())
        {
            accountRepository.saveAndFlush(account);
            return accountRepository.findById(account.getUsername()).get();
        }else{
            return null;
        }
    }

    /**
     * Disable account in system.
     * @param id the account's id
     * @return updated account if disable success, otherwise return null.
     */
    public Account disableAccount(String id){
        var account = accountRepository.findById(id);
        if(account.isPresent()){
            account.get().setEnabled(0);

            return accountRepository.saveAndFlush(account.get());
        }else{
            return null;
        }
    }

    /**
     * Enable account in system.
     * @param id the account's id
     * @return updated account if enable success, otherwise return null.
     */
    public Account enableAccount(String id){
        var account = accountRepository.findById(id);
        if(account.isPresent()){
            account.get().setEnabled(1);
            return accountRepository.saveAndFlush(account.get());

        }else{
            return null;
        }
    }

    /**
     * Update a account's authoritys.
     * @param username the account's username.
     * @param authorities the list new authorities
     * @return
     */
    public Account updateRole(String username, List<Authority> authorities){
        var account = accountRepository.findById(username);
        if(!account.isPresent()){
            return null;
        }else{
            account.get().setAuthorities(authorities);
            return accountRepository.saveAndFlush(account.get());
        }
    }
}
