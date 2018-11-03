package io.pivotal.mooderator.result

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.LocalDateTime

@SpringBootTest
class ResultRepositorySpec extends Specification {

    @Autowired
    ResultRepository resultRepository

    def "Should find daily stats"() {
        given:
        resultRepository.save(new Result(questionId: 1, question: 'question', answerId: 1, answer: 'answer', sentDate: LocalDateTime.now()))
        resultRepository.save(new Result(questionId: 1, question: 'question', answerId: 1, answer: 'answer', sentDate: LocalDateTime.now()))

        when:
        def results = resultRepository.findSurveyStatisticsPerDay(1)

        then:
        results.size() == 1
    }
}
