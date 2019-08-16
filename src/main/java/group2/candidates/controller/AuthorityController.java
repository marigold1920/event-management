package group2.candidates.controller;

import group2.candidates.model.data.Authority;
import group2.candidates.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("/authorities")
public class AuthorityController {
    @Autowired
    private AuthorityRepository authorityRepository;

    @PostMapping
    private List<Authority> createAuthorities() {
        authorityRepository.deleteAll();
        List<Authority> list = new ArrayList<>();
        list.add(new Authority("ROLE_ADMIN"));
        list.add(new Authority("ROLE_REPORTER"));
        list.add(new Authority("ROLE_EVENTMANAGER"));
        list.add(new Authority("ROLE_CANDIDATEMANAGER"));
        return authorityRepository.saveAll(list);
    }
}
