package io.pivotal.mooderator.question;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/questions/latest")
    public Question getLatestQuestion() {
        return questionRepository.findAll().get(0);
    }

}
