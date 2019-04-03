package io.pivotal.mooderator.stats


import spock.lang.Specification

class StatsControllerSpec extends Specification {

    def "Should return daily stats for question"() {
        given:
        StatsService statsService = Mock(StatsService)
        StatsController controller = new StatsController(statsService)
        def expectedStats = [new DailyStats(), new DailyStats()]

        when:
        def results = controller.getSurveyDailyStatistics("1")

        then:
        1 * statsService.loadDailyStatisticsForQuestion(1L) >> expectedStats
        results == expectedStats
    }

    def "Should return daily stats for latest question"() {
        given:
        StatsService statsService = Mock(StatsService)
        StatsController controller = new StatsController(statsService)
        def expectedStats = [new DailyStats(), new DailyStats()]

        when:
        def results = controller.getSurveyDailyStatistics("latest")

        then:
        1 * statsService.loadDailyStatistics() >> expectedStats
        results == expectedStats
    }

    def "Should throw an exception if the id is not valid"() {
        given:
        StatsService statsService = Mock(StatsService)
        StatsController controller = new StatsController(statsService)

        when:
        controller.getSurveyDailyStatistics("wrong_id")

        then:
        thrown(IllegalQuestionIdException)
    }
}
