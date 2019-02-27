package io.pivotal.mooderator.stats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<DailyStats, Integer> {
    List<DailyStats> findAllByQuestionId(Long questionId);
}
