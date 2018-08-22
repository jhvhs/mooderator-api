package io.pivotal.mooderator.question;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
public class AppStartupRunner implements ApplicationRunner {

    private final QuestionRepository questionRepository;

    public AppStartupRunner(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Question question = new Question();
        question.setId(1L);
        question.setSentence("is this a question?");
        question.setAnswers(
                asList(new Answer(1L, "yes"),
                        new Answer(2L, "no"))
        );

        questionRepository.save(question);
    }
}
