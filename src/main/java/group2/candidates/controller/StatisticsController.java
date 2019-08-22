package group2.candidates.controller;

import antlr.collections.List;
import group2.candidates.model.data.Section;
import group2.candidates.model.data.SectionHistory;
import group2.candidates.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("report")
public class StatisticsController {

    private StatisticsService statisticsService;

    @Autowired
    public void setStatisticsService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping(value = "subSubjectType", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Map<String, Integer> reportSubSubjectType(){
        return statisticsService.reportSubsubjectType();
    }

    @GetMapping(value = "campusLink", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Map<String, Integer> reportCampusLink(){
        return statisticsService.reportCampusLinkProgram();
    }

    @GetMapping(value = "event-status", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Map<String, Integer> reportEventStatus(){
        return statisticsService.reportEventStatus();
    }

    @GetMapping(value = "section-status", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Map<String, Integer> reportSectionStatus(){
        return statisticsService.reportSectionStatus();
    }

    @GetMapping(value ="contracttype/{history}/{now}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Collection<Section> getSection(@PathVariable("history") String oldContractType, @PathVariable("now") String currentContracttype){
        return statisticsService.statisticContracType(oldContractType, currentContracttype);
    }
}
