package io.pivotal.mooderator.result;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode
@Getter
@Setter
@Entity
public class Result {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Long id;

    @Column
    @NotNull
    public Long questionId;

    @Column
    @NotNull
    public String question;

    @Column
    @NotNull
    public String answer;
}
