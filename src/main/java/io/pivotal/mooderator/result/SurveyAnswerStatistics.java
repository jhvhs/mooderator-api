package io.pivotal.mooderator.result;

import java.time.LocalDate;
import java.util.Date;

public class SurveyAnswerStatistics {

    private String question;
    private Long questionId;
    private String answer;
    private Long results;
    private LocalDate day;

    public SurveyAnswerStatistics(String question, Long questionId, String answer, Long results, Date day) {
        this.question = question;
        this.questionId = questionId;
        this.answer = answer;
        this.results = results;
        this.day = ((java.sql.Date)day).toLocalDate();
    }

    public SurveyAnswerStatistics(String question, Long questionId, String answer, Long results) {
        this.question = question;
        this.questionId = questionId;
        this.answer = answer;
        this.results = results;
    }

    public SurveyAnswerStatistics() {}

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getResults() {
        return results;
    }

    public void setResults(Long results) {
        this.results = results;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }
}
