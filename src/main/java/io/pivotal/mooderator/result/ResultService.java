package io.pivotal.mooderator.result;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class ResultService {

    private ResultRepository repository;

    public ResultService(ResultRepository repository) {
        this.repository = repository;
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
        repository.find
        return repository.findSurveyStatistics();
    }
}
