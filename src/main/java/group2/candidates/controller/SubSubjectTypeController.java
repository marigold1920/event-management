package group2.candidates.controller;

import group2.candidates.model.data.SubSubjectType;
import group2.candidates.service.SubSubjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class SubSubjectTypeController {

    private SubSubjectTypeService subSubjectTypeService;

    @GetMapping(value = "skills", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<SubSubjectType> loadAllSubSubjectTypes() {

        return subSubjectTypeService.loadAllSubSubjectTypes();
    }

    @PostMapping(value = "skills", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<SubSubjectType> saveSubSubjectTypes(@RequestBody Collection<SubSubjectType> subSubjectTypes) {

        return subSubjectTypes.stream()
                .map(subSubjectTypeService::saveSubSubjectType)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "subSubjectTypeNames", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<String> getAllSubjectTypeName(){
        return subSubjectTypeService.getAllSubSubjectTypeName();
    }
    @Autowired
    public void setSubSubjectTypeService(SubSubjectTypeService subSubjectTypeService) {
        this.subSubjectTypeService = subSubjectTypeService;
    }
}
