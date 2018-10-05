package io.pivotal.mooderator.question

import spock.lang.Specification


class QuestionServiceSpec extends Specification {


    def "Should load question from persistence storage"() {
        given:
        def expectedQuestions = [new Question(id: 1), new Question(id: 2)]
        QuestionRepository questionRepository = Mock(QuestionRepository)
        QuestionService questionService = new QuestionService(questionRepository)

        when:
        def questions = questionService.loadQuestions()

        then:
        1 * questionRepository.findAllByOrderByIdDesc() >> expectedQuestions
        questions == expectedQuestions

    }
}
