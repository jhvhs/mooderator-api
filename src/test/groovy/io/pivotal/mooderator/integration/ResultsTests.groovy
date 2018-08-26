package io.pivotal.mooderator.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResultsTests extends Specification {

    @Autowired
    private TestRestTemplate restTemplate

    def requestBody = [
            questionId: 1L,
            question  : "a question",
            answer    : "yes"
    ]

    void cleanup() {
        restTemplate.delete("/results")
    }

    def "Should save a result"() {
        when:
        def response = postResult()

        then:
        response.statusCode == CREATED
        response.body.id == 1L
        response.body.questionId == requestBody.questionId
        response.body.question == requestBody.question
        response.body.answer == requestBody.answer
        response.body.sentDate ==~ '^(-?(?:[1-9][0-9]*)?[0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(.[0-9]+)?(Z)?$'
    }

    def "Should return saved results"() {
        when:
        def response = postResult()

        then:
        response.statusCode == CREATED

        when:
        response = restTemplate.getForEntity("/results", List.class)

        then:
        response.statusCode == OK
        response.body.size() == 1
        response.body[0].id != null
        response.body[0].questionId == requestBody.questionId
        response.body[0].question == requestBody.question
        response.body[0].answer == requestBody.answer
        response.body[0].sentDate != null
    }

    private ResponseEntity<Map> postResult() {
        restTemplate.postForEntity("/results", requestBody, Map.class)
    }
}
