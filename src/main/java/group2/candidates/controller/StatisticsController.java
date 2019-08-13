package group2.candidates.controller;

import group2.candidates.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("report")
public class StatisticsController {

    private StatisticsService statisticsService;

    @Autowired
    public void setStatisticsService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping(value = "subSubjectType", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Map<String, Integer> reportSubSubjectType(){
        return statisticsService.reportSubsubjectType();
    }

    @GetMapping(value = "campusLink", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Map<String, Integer> reportCampusLink(){
        return statisticsService.reportCampusLinkProgram();
    }

    @GetMapping(value = "event-status", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Map<String, Integer> reportEventStatus(){
        return statisticsService.reportEventStatus();
    }

    @GetMapping(value = "section-status", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Map<String, Integer> reportSectionStatus(){
        return statisticsService.reportSessionStatus();
    }
}
