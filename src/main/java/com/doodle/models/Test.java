package com.doodle.models;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Tests")
@Table(name="test", schema = "public")
@ToString
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "members_tests",
            joinColumns = { @JoinColumn(name = "members") },
            inverseJoinColumns = { @JoinColumn(name = "ownedTests") }
    )
    private Set<User> members;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Question> questions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Result> results = new HashSet<>();

    private Double maxBall;

    private Integer seconds;

    public void addResult(Result result){
        this.results.add(result);
        if (result.getTest() != this) {
            result.setTest(this);
        }
    }

    public void removeResult(Result result){
        this.results.remove(result);
        result.setTest(null);
    }
}
