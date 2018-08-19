package io.pivotal.mooderator.result;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class ResultController {

    @ResponseStatus(CREATED)
    @PostMapping("/results")
    @ResponseBody
    public Result saveResult(@Valid @RequestBody Result result) {
        result.setId(1L);
        return result;
    }
}
