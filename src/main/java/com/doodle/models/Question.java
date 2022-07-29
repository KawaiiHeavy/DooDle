package com.doodle.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "questions")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Question {

    @Id
    @GeneratedValue()
    @Column(name="id")
    private UUID id;

    private String questionText;

    private Double scoreWeight;

    @OneToOne(fetch = FetchType.LAZY)
    private Test test;

    private String imageUrl;


}
