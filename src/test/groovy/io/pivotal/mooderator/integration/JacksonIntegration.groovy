package io.pivotal.mooderator.integration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.ZoneId
import java.time.ZonedDateTime

@SpringBootTest
class JacksonIntegration extends Specification {

    @Autowired
    ObjectMapper objectMapper

    def "Should parse date to iso-date string"() {
        def date = ZonedDateTime.of(2018, 3, 27, 12, 0, 0, 0, ZoneId.of('UTC'))
        when:
        def isoDate = objectMapper.writeValueAsString(['date':date])

        then:
        isoDate == '{"date":"2018-03-27T12:00:00Z"}'
    }
}
