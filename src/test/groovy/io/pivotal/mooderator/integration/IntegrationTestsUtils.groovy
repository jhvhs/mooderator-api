package io.pivotal.mooderator.integration

class IntegrationTestsUtils {
    static postResult(restTemplate, requestBody) {
        restTemplate.postForEntity("/results", requestBody, Map.class)
    }
}
