package io.pivotal.mooderator.integration

import com.fasterxml.jackson.databind.ObjectMapper
import io.pivotal.mooderator.result.ResultController
import io.pivotal.mooderator.result.ResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Import([IntegrationTestMockingConfig])
@WebMvcTest(value = [ResultController])
class ResultsValidation extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    private ObjectMapper mapper

    def "Should validate request body"() {
        expect:
        mvc.perform(post("/results")
                .content(mapper.writeValueAsString(body))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())

        where:
        body                                     | _
        []                                       | _
        [question: "question", answer: "answer"] | _
        [questionId: 1L, answer: "answer"]       | _
        [questionId: 1L, question: "question"]   | _
    }

    def "Should accept a valid body"() {
        expect:
        mvc.perform(post("/results")
                .content(mapper.writeValueAsString([questionId: 1L, question: "question", answer: "answer"]))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
    }
}

@TestConfiguration
class IntegrationTestMockingConfig {
    private DetachedMockFactory factory = new DetachedMockFactory()

    @Bean
    ResultService resultRepository() {
        factory.Mock(ResultService)
    }
}