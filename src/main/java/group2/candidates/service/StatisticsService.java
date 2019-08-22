package group2.candidates.service;

import group2.candidates.model.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatisticsService {

    private EventService eventService;
    private SectionService sectionService;
    private SubSubjectTypeService subSubjectTypeService;
    private CampusLinkProgramService campusLinkProgramService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setSubSubjectTypeService(SubSubjectTypeService subSubjectTypeService) {
        this.subSubjectTypeService = subSubjectTypeService;
    }

    @Autowired
    public void setCampusLinkProgramService(CampusLinkProgramService campusLinkProgramService) {
        this.campusLinkProgramService = campusLinkProgramService;
    }

    @Autowired
    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    /**
     * This function is used to report number of candidate belong to subsubjectype.
     * @return Map report subsubject type.
     */
    public Map<String, Integer> reportSubsubjectType(){
        List<Event> eventList = eventService.getAllEvents();
        var subSubjectTypeList = subSubjectTypeService.getAllSubSubjectTypes();
        Map<String, Integer> result = new HashMap<>();
        int num = 0;
        for (SubSubjectType subSubjectType : subSubjectTypeList) {
            for (Event event : eventList) {
                if (event.getSubSubjectType().getSubSubjectTypeId().equals(subSubjectType.getSubSubjectTypeId())) {
                    num += event.getCandidates().size();
                }
            }
            result.put(subSubjectType.getSubSubjectTypeName(), num);
            num = 0;
        }
        return result;
    }

    /**
     * This function is used to report number of candidate belong to campus link program.
     * @return a Map reported campus link program.
     */
    public Map<String, Integer> reportCampusLinkProgram(){
        List<Event> eventList = eventService.getAllEvents();
        var campusLinkProgramList =  campusLinkProgramService.getAllCampusLinkProgram();
        Map<String, Integer> result = new HashMap<>();
        int num = 0;
        for (CampusLinkProgram campusLinkProgram : campusLinkProgramList) {
            for (Event event : eventList) {
                if (event.getCampusLinkProgram().getCode().equals(campusLinkProgram.getCode())) {
                    num++;
                }
            }
            result.put(campusLinkProgram.getName(), num);
            num = 0;
        }
        return result;

    }
    /**
     * This function is used to report number of event's status code.
     * @return a Map reported event's status code.
     */
    public Map<String, Integer> reportEventStatus(){
        List<Event> eventList = eventService.getAllEvents();
        Map<String, Integer> result = new HashMap<>();
        for (Event event : eventList) {
            if(result.containsKey(event.getEventStatus())){
                result.put(event.getEventStatus(), result.get(event.getEventStatus()) + 1);
            }else{
                result.put(event.getEventStatus(), 1);
            }
        }
        
        return result;

    }

    /**
     * This function is used to report number of section's status.
     * @return a Map reported section's status.
     */
    public Map<String, Integer> reportSectionStatus(){
        var sectionList = sectionService.getAllSection();
        Map<String, Integer> result = new HashMap<>();
        for (Section section : sectionList) {
            if(result.containsKey(section.getCandidateStatus())){
                result.put(section.getCandidateStatus(), result.get(section.getCandidateStatus()) + 1);
            }else{
                result.put(section.getCandidateStatus(), 1);
            }
        }

        return result;
    }

    public Collection<Section> statisticContracType(String oldContractType, String currentContractType) {

        Collection<Section> sections = sectionService.getSectionByContractType(currentContractType);
        Collection<Section> sectionsResult = null;
        for (Section section: sections) {
            if(section.getSectionHistory().getContractType().equals(oldContractType)){
                if(sectionsResult == null){
                    sectionsResult = new ArrayList<>();
                }
                sectionsResult.add(section);
            }
        }
        return sectionsResult;
    }
}
