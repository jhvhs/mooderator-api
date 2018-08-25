package io.pivotal.mooderator.result;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RequestMapping("/results")
@RestController
public class ResultController {

    private final ResultRepository resultRepository;

    public ResultController(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @ResponseStatus(CREATED)
    @PostMapping
    @ResponseBody
    public Result saveResult(@Valid @RequestBody Result result) {
        return resultRepository.save(result);
    }

    @GetMapping
    @ResponseBody
    public List<Result> getResults() {
        return resultRepository.findAll();
    }

    @Profile("test")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping
    public void deleteResults() {
        resultRepository.deleteAll();
    }
}
