package com.doodle.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "answers")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Answer {

    @Id
    @GeneratedValue()
    @Column(name="id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    private String answerText;

    private Boolean correct;

}
