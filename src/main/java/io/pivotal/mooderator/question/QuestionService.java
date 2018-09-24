package io.pivotal.mooderator.question;

import org.springframework.stereotype.Component;

@Component
public class QuestionService {

    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question findLastQuestion() {
        return questionRepository.findAllByOrderByIdDesc().get(0);
    }

    public Question save(Question question) {
        return questionRepository.save(question);
    }

}
