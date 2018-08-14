package io.pivotal.mooderator.question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);

    @GetMapping("/questions/latest")
    public Question getLatestQuestion() {
        LOGGER.info("Returning current question");
        Question question = new Question();
        question.setSentence("is this a question?");
        return question;
    }

}
