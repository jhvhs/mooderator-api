package io.pivotal.mooderator.result;

import io.pivotal.mooderator.question.QuestionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class ResultService {

    private QuestionService questionService;
    private ResultRepository repository;

    public ResultService(ResultRepository repository, QuestionService questionService) {
        this.repository = repository;
        this.questionService = questionService;
    }

    public Result save(Result result) {
        result.setSentDate(LocalDateTime.now(ZoneId.of(ZoneOffset.UTC.toString())));
        return repository.save(result);
    }

    public List<Result> loadAll() {
        return repository.findAll();
    }

    public void clearAll() {
        repository.deleteAll();
    }

    public List<SurveyAnswerStatistics> loadStatistics() {
        return repository.findSurveyStatistics(questionService.findLastQuestion().getId());
    }
}
