package io.pivotal.mooderator.question;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.loadQuestions();
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
