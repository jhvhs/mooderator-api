package io.pivotal.mooderator.stats;

import io.pivotal.mooderator.question.Question;
import io.pivotal.mooderator.question.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<DailyStats> loadDailyStatisticsForQuestion(Long questionId) {
        Optional<Question> question = questionService.findQuestion(questionId);

        if(question.isPresent()) {
            return repository.findAllByQuestionId(question.get().getId());
        }

        throw new QuestionNotFoundException(String.format("Question %d not found", questionId));
    }

}
