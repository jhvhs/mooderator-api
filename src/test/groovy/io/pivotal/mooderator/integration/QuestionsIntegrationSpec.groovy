package io.pivotal.mooderator.integration


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestionsIntegrationSpec extends Specification {

    @Autowired
    private TestRestTemplate restTemplate

    def "Should save a new question"() {
        when:
        def response = createQuestion()

        then:
        response.statusCode == CREATED
        response.body.id != null
        response.body.answers.size() == 3
    }

    def "Should return all questions"() {
        given:
        createQuestion('is this second to last?')
        createQuestion('is this the last?')

        when:
        def response = restTemplate.getForEntity("/questions", List.class)

        then:
        response.getStatusCode() == OK
        def questions = response.getBody()
        !questions.isEmpty()
        questions[0].sentence == 'is this the last?'
        questions[0].answers.size() == 3
        questions[0].answers[0].id != null
        questions[0].answers[0].value == 'yes'
        questions[0].answers[1].id != null
        questions[0].answers[1].value == 'maybe'
        questions[0].answers[2].id != null
        questions[0].answers[2].value == 'no'
        questions[1].sentence == 'is this second to last?'
        questions[1].answers.size() == 3
        questions[1].answers[0].id != null
        questions[1].answers[0].value == 'yes'
        questions[1].answers[1].id != null
        questions[1].answers[1].value == 'maybe'
        questions[1].answers[2].id != null
        questions[1].answers[2].value == 'no'
    }

    private ResponseEntity createQuestion(sentence = 'is this a question?'){
        def question = ['sentence' : sentence, answers: [
                ['value' : 'yes'],
                ['value' : 'maybe'],
                ['value' : 'no']
        ]]

        restTemplate.postForEntity("/questions", question, Map.class)
    }
}
