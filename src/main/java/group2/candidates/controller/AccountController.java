package group2.candidates.controller;

import group2.candidates.model.data.Account;
import group2.candidates.model.data.Authority;
import group2.candidates.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * get all accounts
     * @return Set all accounts
     */
    @GetMapping(value = "/accounts", produces = { "application/json;charset=UTF-8" })
    public List<Account> getAllAccount(){
        return accountService.loadAllAccounts();
    }

    /**
     * Add a new account to system.
     * @param account the new account
     * @return true if add success, otherwise return false
     */
    @PostMapping(value = "/save", produces = { "application/json;**charset=UTF-8**"} )
    public boolean saveAccount(@RequestBody Account account){

        return accountService.addAccount(account);
    }

    /**
     * Update the new account.
     * @param account the updated account.
     * @return return the new account if update success, otherwise return false.
     */
    @PatchMapping(value ="/update", produces = { "application/json;**charset=UTF-8**"})
    public Account updateAccount(@RequestBody Account account){
        return accountService.updateAccount(account);
    }

    /**
     * Disable an account.
     * @param username the account's username.
     * @return true if disable success, otherwise false.
     */
    @PatchMapping(value = "/disable/{username}")
    public boolean disableAccount(@PathVariable("username") String username){
        return accountService.disableAccount(username);
    }

    /**
     * Enable an account
     * @param username the account's username
     * @return true if enable success, otherwise false.
     */
    @PatchMapping(value = "/enable/{username}")
    public boolean enableAccount(@PathVariable("username") String username){
        return accountService.enableAccount(username);
    }

    /**
     *
     * @param authoritiesID the array account's authority ids.
     * @param username the username.
     * @return return true if update success, otherwise return false.
     */
    @PatchMapping(value = "/update-roles", produces = { "application/json;**charset=UTF-8**"} )
    public boolean updateRoles(@RequestBody List<Authority> authoritiesID, @Param("username") String username){
        return accountService.updateRole(username, authoritiesID);
    }
}
