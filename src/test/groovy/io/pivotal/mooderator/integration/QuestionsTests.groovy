package io.pivotal.mooderator.integration


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestionsTests extends Specification {

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

    def "Should return the last saved question"() {
        given:
        createQuestion()
        createQuestion('are you sure?')

        when:
        def response = restTemplate.getForEntity("/questions/latest", Map.class)

        then:
        response.getStatusCode() == OK
        def body = response.getBody()
        body['id'] != null
        body['sentence'] == 'are you sure?'
        body.answers.size() == 3
        body.answers[0].id != null
        body.answers[0].value == 'yes'
        body.answers[1].id != null
        body.answers[1].value == 'maybe'
        body.answers[2].id != null
        body.answers[2].value == 'no'
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
