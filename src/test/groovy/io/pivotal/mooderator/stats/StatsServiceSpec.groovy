package io.pivotal.mooderator.stats

import io.pivotal.mooderator.question.Question
import io.pivotal.mooderator.question.QuestionService
import spock.lang.Specification

class StatsServiceSpec extends Specification {

    QuestionService questionService
    StatsRepository statsRepository
    StatsService service

    void setup() {
        questionService = Mock(QuestionService)
        statsRepository = Mock(StatsRepository)
        service = new StatsService(questionService, statsRepository)
    }

    def "Should return daily statistics"() {
        given:
        def question = new Question(id: 1L)
        def expectedStatistics = [new DailyStats()]

        when:
        def statistics = service.loadDailyStatistics()

        then:
        1 * questionService.findLastQuestion() >> question
        1 * statsRepository.findAllByQuestionId(question.id) >> expectedStatistics
        statistics == expectedStatistics
    }

    def "Should return daily statistics for a question"() {
        given:
        def questionId = 1L
        def question = Optional.of(new Question(id: questionId))
        def expectedStatistics = [new DailyStats()]

        when:
        def statistics = service.loadDailyStatisticsForQuestion(questionId)

        then:
        1 * questionService.findQuestion(questionId) >> question
        1 * statsRepository.findAllByQuestionId(question.get().id) >> expectedStatistics
        statistics == expectedStatistics
    }

    def "Should throw an exception if question does not exist"() {
        given:
        def questionId = 1L

        when:
        service.loadDailyStatisticsForQuestion(questionId)

        then:
        1 * questionService.findQuestion(questionId) >> Optional.empty()
        0 * statsRepository._
        thrown(QuestionNotFoundException)
    }
}
