package group2.candidates.service;

import group2.candidates.model.data.Authority;
import group2.candidates.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {
    private AuthorityRepository authorityRepository;

    @Autowired
    public void setAuthorityRepository(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority getAuthorityById(Integer authorityID){
        if(authorityRepository.findById(authorityID).isPresent()) {
            return authorityRepository.findById(authorityID).get();
        }else{
            return null;
        }
    }
}
