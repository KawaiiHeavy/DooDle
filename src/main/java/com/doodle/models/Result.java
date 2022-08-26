package com.doodle.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Result {

    @Id
    @GeneratedValue()
    @Column(name="id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Test test;

    @ManyToOne(fetch = FetchType.LAZY)
    private User participant;

    @Column(nullable = false)
    private Double score;

}
