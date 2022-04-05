package com.doodle.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Questions")
@Table(name="question", schema = "public")
public class Question {

    @Id
    @GeneratedValue()
    @Column(name="id")
    private UUID id;

    private String questionText;

    private Double scoreWeight;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Answer> possibleAnswers;

    public Question(UUID id, String questionText, Double scoreWeight){
        this.id = id;
        this.questionText = questionText;
        this.scoreWeight = scoreWeight;
    }

}
