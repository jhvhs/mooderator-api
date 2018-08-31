package io.pivotal.mooderator.integration

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import spock.lang.Specification

import static org.springframework.http.HttpStatus.OK

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestionsTests extends Specification {

    @Autowired
    private TestRestTemplate restTemplate

    @Test
    def "Should return question"() {
        when:
        def response = restTemplate.getForEntity("/questions/latest", Map.class)

        then:
        response.getStatusCode() == OK
        def body = response.getBody()
        body['id'] != null
        body['sentence'] == 'How was your day in the office?'
        body.answers.size() == 3
        body.answers[0].id == 1
        body.answers[0].value == 'Good'
        body.answers[1].id == 2
        body.answers[1].value == 'So so'
        body.answers[2].id == 3
        body.answers[2].value == 'Bad'
    }
}
