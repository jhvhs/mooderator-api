package io.pivotal.mooderator.question;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
class Question {

    private Long id;
    private String sentence;
    private List<Answer> answers;
}
