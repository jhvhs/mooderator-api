package io.pivotal.mooderator.stats;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService resultService) {
        this.statsService = resultService;
    }

    @Deprecated
    @GetMapping("/results/daily-statistics")
    @ResponseBody
    public List<DailyStats> getSurveyDailyStatistics() {
        return statsService.loadDailyStatistics();
    }

    @GetMapping("/questions/{questionId}/daily-statistics")
    @ResponseBody
    public List<DailyStats> getSurveyDailyStatistics(@PathVariable("questionId") String question) {
        if ("latest".equalsIgnoreCase(question)) {
            return statsService.loadDailyStatistics();
        }

        if (isNotNumeric(question)) {
            throw new IllegalQuestionIdException(String.format("Question id %s is not a valid value", question));
        }

        return statsService.loadDailyStatisticsForQuestion(Long.valueOf(question));
    }

    private boolean isNotNumeric(String question) {
        return !question.matches("\\d+");
    }

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(IllegalQuestionIdException.class)
    public void handleIllegalQuestionIdException() {
    }

    @ResponseStatus(value = NOT_FOUND)
    @ExceptionHandler(QuestionNotFoundException.class)
    public void handleQuestionNotFoundException() {
    }

}
