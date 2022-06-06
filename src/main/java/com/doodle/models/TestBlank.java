package com.doodle.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class TestBlank {

    private UUID id;
    private String title;
    private User creator;
    private Set<User> members;
    private Set<Question> questions;
    private Set<QuestionBlank> questionBlanks;
    private Set<Result> results;
    private Double maxBall;
    private Integer seconds;
    private User participant;

}
