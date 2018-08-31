package io.pivotal.mooderator.result;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EqualsAndHashCode
@Getter
@Setter
@Entity
public class Result {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    @NotNull
    private Long questionId;

    @Column
    @NotNull
    private String question;

    @Column
    @NotNull
    private Long answerId;

    @Column
    @NotNull
    private String answer;

    @Column
    private LocalDateTime sentDate;
}
