package io.pivotal.mooderator.result;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode
@Getter
@Setter
public class Result {
    public Long id;

    @NotNull
    public Long questionId;
    @NotNull
    public String question;
    @NotNull
    public String answer;
}
