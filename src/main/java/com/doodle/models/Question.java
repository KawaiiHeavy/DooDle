package com.doodle.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.util.HashSet;
import java.util.List;
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

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Answer> answers;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ImageModel image;


}
