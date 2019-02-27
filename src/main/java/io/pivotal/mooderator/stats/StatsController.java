package io.pivotal.mooderator.stats;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService resultService) {
        this.statsService = resultService;
    }

    @GetMapping("/results/daily-statistics")
    @ResponseBody
    public List<DailyStats> getSurveyDailyStatistics() {
        return statsService.loadDailyStatistics();
    }

}
