package io.pivotal.mooderator.question;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/latest")
    public Question getLatestQuestion() {
        return questionService.findLastQuestion();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Question storeQuestion(@RequestBody @Valid Question question) {
        return questionService.save(question);
    }
}
