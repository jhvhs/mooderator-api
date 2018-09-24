package io.pivotal.mooderator.result;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
//
//    @Query(nativeQuery = true, value =
//            "SELECT " +
//                    "    v.answer AS answer, COUNT(v) AS cnt " +
//                    "FROM " +
//                    "    Survey v " +
//                    "GROUP BY " +
//                    "    v.answer")


    @Query(value = "select question, question_id, answer, COUNT(r) as results from result r where question_id = ?1 group by answer")
    List<SurveyAnswerStatistics> findSurveyStatistics(Long questionId);

}
