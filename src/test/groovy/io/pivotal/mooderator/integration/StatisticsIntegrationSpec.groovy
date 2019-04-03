package io.pivotal.mooderator.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Specification

import static io.pivotal.mooderator.integration.IntegrationTestsUtils.postResult
import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class StatisticsIntegrationSpec extends Specification {

    def question = ['sentence': 'questions????', answers: [
            ['value': 'yes'],
            ['value': 'maybe'],
            ['value': 'no']
    ]]

    @Autowired
    TestRestTemplate restTemplate

    def "Should return daily stats for latest questions"() {
        when:
        def response = createQuestion()

        then:
        response.statusCode == CREATED
        def questionId = response.body.id
        def answerId = response.body.answers[0].id

        when:
        response = postResult(restTemplate, [
                questionId: questionId,
                question  : question.sentence,
                answerId  : answerId,
                answer    : question.answers[0].value
        ])

        then:
        response.statusCode == CREATED

        when:
        response = restTemplate.getForEntity("/questions/latest/daily-statistics", List.class)

        then:
        response.statusCode == OK
        response.body.size() == 1
        response.body[0].questionId == questionId
        response.body[0].question == question.sentence
        response.body[0].answer == question.answers[0].value
        response.body[0].results == 1
    }

    def "Should return daily stats for question"() {
        when:
        def response = createQuestion()

        then:
        response.statusCode == CREATED
        def questionId = response.body.id
        def answerId = response.body.answers[0].id

        when:
        response = postResult(restTemplate, [
                questionId: questionId,
                question  : question.sentence,
                answerId  : answerId,
                answer    : question.answers[0].value
        ])

        then:
        response.statusCode == CREATED

        when:
        response = restTemplate.getForEntity("/questions/${questionId}/daily-statistics", List.class)

        then:
        response.statusCode == OK
        response.body.size() == 1
        response.body[0].questionId == questionId
        response.body[0].question == question.sentence
        response.body[0].answer == question.answers[0].value
        response.body[0].results == 1
    }

    def "Should return 404 if question does not exist"() {
        when:
        def response = restTemplate.getForEntity("/questions/${Integer.MAX_VALUE}/daily-statistics", List.class)

        then:
        response.statusCode == NOT_FOUND
    }

    def "Should return 400 if question does not exist"() {
        when:
        def response = restTemplate.getForEntity("/questions/wrong-question/daily-statistics", List.class)

        then:
        response.statusCode == BAD_REQUEST
    }

    private ResponseEntity createQuestion() {
        restTemplate.postForEntity("/questions", question, Map.class)
    }
}
