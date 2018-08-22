package io.pivotal.mooderator.result;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class ResultController {

    private final ResultRepository resultRepository;

    public ResultController(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @ResponseStatus(CREATED)
    @PostMapping("/results")
    @ResponseBody
    public Result saveResult(@Valid @RequestBody Result result) {
        return resultRepository.save(result);
    }
}
