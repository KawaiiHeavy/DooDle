package com.doodle.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Answers")
@Table(name="answer", schema = "public")
public class Answer {

    @Id
    @GeneratedValue()
    @Column(name="id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question questionId;

    private String answerText;

    private Boolean correct;

}
