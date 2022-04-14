package com.doodle.models;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Tests")
@Table(name="test", schema = "public")
public class Test {

    @Id
    @GeneratedValue()
    @Column(name="id")
    private UUID id;

    @Column(nullable = false)
    private String title;

    @CreatedBy
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private User creator;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "members_tests",
            joinColumns = { @JoinColumn(name = "members") },
            inverseJoinColumns = { @JoinColumn(name = "ownedTests") }
    )
    private Set<User> members;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Result> results = new HashSet<>();

    private Double maxBall;

    private Integer seconds;

    public void addResult(Result result){
        this.results.add(result);
        result.setTest(this);
    }

    public void removeResult(Result result){
        this.results.remove(result);
        result.setTest(null);
    }

}
