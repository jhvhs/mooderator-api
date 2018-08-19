package io.pivotal.mooderator.result;

import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class ResultController {

    @ResponseStatus(CREATED)
    @PostMapping("/results")
    @ResponseBody
    public Result saveResult(@RequestBody Result result){
        result.setId(1L);
        return result;
    }
}
