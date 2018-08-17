package io.pivotal.mooderator.question;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    @GetMapping("/questions/latest")
    public Question getLatestQuestion() {
        Question question = new Question();
        question.setId(1L);
        question.setSentence("is this a question?");
        return question;
    }

}
