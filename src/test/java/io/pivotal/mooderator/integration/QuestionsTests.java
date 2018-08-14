package io.pivotal.mooderator.integration;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionsTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnQuestion() {
        ResponseEntity<Map> response = restTemplate.getForEntity("/questions/latest", Map.class);

        assertThat(response.getStatusCode(), is(equalTo(OK)));
        assertThat(response.getBody().get("sentence"), is(equalTo("is this a question?")));
    }
}
