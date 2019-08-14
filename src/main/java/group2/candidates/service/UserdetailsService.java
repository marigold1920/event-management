package group2.candidates.service;

import group2.candidates.model.data.Account;
import group2.candidates.model.data.CustomUserdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserdetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByUsername(username);
        System.out.println(account.toString());
        UserDetails userDetails = new CustomUserdetails(account);
        System.out.println(userDetails.toString());
        return userDetails;
    }
}
