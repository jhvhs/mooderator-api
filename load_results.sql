select question, question_id, answer, count(*)
from result
where question_id = (select id from question order by id limit 1)
group by answer;