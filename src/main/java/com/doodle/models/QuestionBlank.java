package com.doodle.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class QuestionBlank {

    private UUID id;
    private String questionText;
    private Double scoreWeight;
    private List<Answer> answers;
    private List<Answer> userAnswers;

}
