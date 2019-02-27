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


}
