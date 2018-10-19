-- results by answer
select question, question_id, answer, count(*) as count from result where question_id = (select id from question order by id limit 1) group by answer;

-- results by answer and date
select question, question_id, answer_id, answer, date(sent_date) as dat, count(*) as count from result where question_id = (select id from question order by id limit 1) group by answer, dat order by dat;