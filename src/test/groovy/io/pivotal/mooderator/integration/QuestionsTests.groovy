package io.pivotal.mooderator.integration

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static org.springframework.http.HttpStatus.OK

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestionsTests extends Specification {

    @Autowired
    private TestRestTemplate restTemplate

    @Test
    def "Should return question"() {
        when:
        ResponseEntity<Map> response = restTemplate.getForEntity("/questions/latest", Map.class)

        then:
        response.getStatusCode() == OK
        def responseBody = response.getBody()
        responseBody['id'] != null
        responseBody['sentence'] == 'is this a question?'
    }
}
