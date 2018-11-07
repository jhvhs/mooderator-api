package io.pivotal.mooderator.result;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    @Query(value = "select new io.pivotal.mooderator.result.SurveyAnswerStatistics(r.question, r.questionId, r.answer, COUNT(r) AS results) from Result r where r.questionId = ?1 group by r.question, r.answer")
    List<SurveyAnswerStatistics> findSurveyStatistics(Long questionId);

    @Query(value = "select new io.pivotal.mooderator.result.SurveyAnswerStatistics(r.question, r.questionId, r.answer, count(r) as results, date(r.sentDate) as day) from Result r where r.questionId = ?1 group by r.answer, date(r.sentDate) order by day asc")
    List<SurveyAnswerStatistics> findSurveyStatisticsPerDay(Long questionId);
}
