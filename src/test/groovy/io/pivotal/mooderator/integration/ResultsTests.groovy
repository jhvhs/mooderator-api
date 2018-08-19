package io.pivotal.mooderator.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import spock.lang.Specification

import static org.springframework.http.HttpStatus.CREATED

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResultsTests extends Specification {

    @Autowired
    private TestRestTemplate restTemplate

    def "Should save a result"() {
        given:
        def requestBody = [
                questionId: 1L,
                question  : "a question",
                answer    : "yes"
        ]

        when:
        def response = restTemplate.postForEntity("/results", requestBody, Map.class)

        then:
        response.statusCode == CREATED
        response.body.id == 1L
        response.body.questionId == requestBody.questionId
        response.body.question == requestBody.question
        response.body.answer == requestBody.answer
    }
}
