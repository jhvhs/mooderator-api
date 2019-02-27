package io.pivotal.mooderator.stats;

import io.pivotal.mooderator.question.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsService {

    private final QuestionService questionService;
    private final StatsRepository repository;

    public StatsService(QuestionService questionService, StatsRepository repository) {
        this.questionService = questionService;
        this.repository = repository;
    }

    public List<DailyStats> loadDailyStatistics() {
        return repository.findAllByQuestionId(questionService.findLastQuestion().getId());
    }

}
