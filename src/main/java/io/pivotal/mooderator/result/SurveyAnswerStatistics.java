package io.pivotal.mooderator.result;

public class SurveyAnswerStatistics {

    private String question;
    private Long questionId;
    private String answer;
    private Long results;

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
}
