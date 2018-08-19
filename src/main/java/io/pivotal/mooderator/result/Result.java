package io.pivotal.mooderator.result;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
public class Result {

    public Long id;
    public Long questionId;
    public String question;
    public String answer;
}
