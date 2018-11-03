package io.pivotal.mooderator.result

import io.pivotal.mooderator.question.Question
import io.pivotal.mooderator.question.QuestionService
import spock.lang.Specification

import java.time.LocalDateTime

class ResultServiceTest extends Specification {

    QuestionService questionService
    ResultRepository resultRepository
    ResultService service

    void setup() {
        questionService = Mock(QuestionService)
        resultRepository = Mock(ResultRepository)
        service = new ResultService(resultRepository, questionService)
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

    def "Should return statistics"() {
        given:
        def question = new Question(id: 1L)
        def expectedStatistics = [new SurveyAnswerStatistics()]

        when:
        def statistics = service.loadStatistics()

        then:
        1 * questionService.findLastQuestion() >> question
        1 * resultRepository.findSurveyStatistics(question.id) >> expectedStatistics
        statistics == expectedStatistics
    }

    def "Should return daily statistics"() {
        given:
        def question = new Question(id: 1L)
        def expectedStatistics = [new SurveyAnswerStatistics()]

        when:
        def statistics = service.loadDailyStatistics()

        then:
        1 * questionService.findLastQuestion() >> question
        1 * resultRepository.findSurveyStatisticsPerDay(question.id) >> expectedStatistics
        statistics == expectedStatistics
    }

    def "Should delete all results"() {
        when:
        service.clearAll()

        then:
        1 * resultRepository.deleteAll()
    }
}
