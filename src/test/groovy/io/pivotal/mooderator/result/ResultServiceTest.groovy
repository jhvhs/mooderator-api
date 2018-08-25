package io.pivotal.mooderator.result

import spock.lang.Specification

import java.time.LocalDateTime

class ResultServiceTest extends Specification {

    ResultRepository resultRepository
    ResultService service

    void setup() {
        resultRepository = Mock(ResultRepository)
        service = new ResultService(resultRepository)
    }

    def "Should return all results"() {
        given:
        def expectedResults = [new Result(), new Result()]

        when:
        def results = service.loadAll()

        then:
        1 * resultRepository.findAll() >> expectedResults
        results == expectedResults
    }

    def "Should set creation date before saving"() {
        given:
        Result result = new Result(questionId: 1L, question: 'foo', answer: 'bar')
        Result expectedResult = new Result(id: 1, questionId: 1L, question: 'foo', answer: 'bar', sentDate: LocalDateTime.now())

        when:
        def savedResult = service.save(result)

        then:
        1 * resultRepository.save(_ as Result) >> {Result r ->
            assert r.questionId == result.questionId
            assert r.question == result.question
            assert r.answer == result.answer
            assert r.sentDate != null

            expectedResult
        }
        savedResult == expectedResult
    }

    def "Should delete all results"() {
        when:
        service.clearAll()

        then:
        1 * resultRepository.deleteAll()
    }
}
