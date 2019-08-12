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

    // @Autowired
    // public void setAuthorityRepository(AuthorityRepository authorityRepository) {
    //     this.authorityRepository = authorityRepository;
    // }

    /**
     * Load all account from system.
     * @return the list all accounts.
     */
    public List<Account> loadAllAccounts(){
        return accountRepository.findAll();
    }


    /**
     * Add new account to system.
     * @param account the new account.
     * @return if add success return true, otherwise return false.
     */
    public boolean addAccount(Account account){
        if(!accountRepository.findById(account.getUsername()).isPresent()){
            accountRepository.saveAndFlush(account);
            return true;
        }else{
            return false;
        }
    }


    /**
     * Update a account.
     * @param account the new account
     * @return the new account if update success otherwise null.
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
     * @return true if disable success, otherwise return false;
     */
    public boolean disableAccount(String id){
        var account = accountRepository.findById(id);
        if(account.isPresent()){
            account.get().setEnabled(0);
            accountRepository.saveAndFlush(account.get());
            return true;
        }else{
            return false;
        }
    }

    /**
     * Enable account in system.
     * @param id the account's id
     * @return true if enable success, otherwise return false;
     */
    public boolean enableAccount(String id){
        var account = accountRepository.findById(id);
        if(account.isPresent()){
            account.get().setEnabled(1);
            accountRepository.saveAndFlush(account.get());
            return true;
        }else{
            return false;
        }
    }

    /**
     * Update a account's authoritys.
     * @param username the account's username.
     * @param authorities the list new authorities
     * @return
     */
    public boolean updateRole(String username, List<Authority> authorities){
        var account = accountRepository.findById(username);
        if(!account.isPresent()){
            return false;
        }else{
            account.get().setAuthorities(authorities);
            accountRepository.saveAndFlush(account.get());
            return true;
        }
    }
}
