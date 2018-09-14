package io.pivotal.mooderator.question;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/latest")
    public Question getLatestQuestion() {
        return questionRepository.findAllByOrderByIdDesc().get(0);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Question storeQuestion(@RequestBody @Valid Question question) {
        return questionRepository.save(question);
    }
}
