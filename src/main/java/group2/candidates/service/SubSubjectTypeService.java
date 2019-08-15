package group2.candidates.service;

import group2.candidates.model.data.SubSubjectType;
import group2.candidates.repository.SubSubjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SubSubjectTypeService {

    private SubSubjectTypeRepository repository;

    public Optional<SubSubjectType> findSubSubjectTypeByName(String subSubjectTypeName) {

        return repository.findSubSubjectTypeByName(subSubjectTypeName);
    }

    public Collection<SubSubjectType> loadAllSubSubjectTypes() {

        return repository.findAll();
    }

    public SubSubjectType saveSubSubjectType(SubSubjectType subSubjectType) {

        return repository.saveAndFlush(subSubjectType);
    }
    
    public Collection<SubSubjectType> getAllSubSubjectTypes() {

        return repository.findAll();
    }

    public Collection<String> getAllSubSubjectTypeName(){
        List<SubSubjectType> subSubjectTypeList = repository.findAll();
        List<String> subSubjectTypeNameList = new ArrayList<>();
        for (SubSubjectType subSubjectType: subSubjectTypeList) {
            subSubjectTypeNameList.add(subSubjectType.getSubSubjectTypeName());
        }

        return subSubjectTypeNameList;
    }

    @Autowired
    public void setRepository(SubSubjectTypeRepository repository) {
        this.repository = repository;
    }
}
