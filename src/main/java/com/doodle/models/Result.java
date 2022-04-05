package com.doodle.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Results")
@Table(name="result", schema = "public")
public class Result {

    @Id
    @GeneratedValue()
    @Column(name="id")
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Test test;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition="uuid not null")
    private User participant;

    @Column(nullable = false)
    private Double score;
}
