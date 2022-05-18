package com.doodle.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "Questions")
@Table(name="question", schema = "public")
public class Question {

    @Id
    @GeneratedValue()
    @Column(name="id")
    private UUID id;

    private String questionText;

    private Double scoreWeight;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Answer> possibleAnswers = new HashSet<>();

    public Question(UUID id, String questionText, Double scoreWeight){
        this.id = id;
        this.questionText = questionText;
        this.scoreWeight = scoreWeight;
    }

    public void addAnswer(Answer answer){
        this.possibleAnswers.add(answer);
        answer.setQuestion(this);
    }

    public void removeAnswer(Answer answer){
        this.possibleAnswers.remove(answer);
        answer.setQuestion(null);
    }
}
