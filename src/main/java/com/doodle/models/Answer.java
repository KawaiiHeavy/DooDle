package com.doodle.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Answers")
@Table(name="answer", schema = "public")
public class Answer {

    @Id
    @GeneratedValue()
    @Column(name="id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Question question;

    private String answerText;

    private Boolean correct;

    public Answer(UUID id, String answerText, Boolean correct) {
        this.id = id;
        this.answerText = answerText;
        this.correct = correct;
    }
}
