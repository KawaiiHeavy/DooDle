package com.doodle.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode
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

    @ManyToOne
    @JoinColumn(name = "creator", nullable = false)
    private User creator;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "members_tests",
            joinColumns = { @JoinColumn(name = "members") },
            inverseJoinColumns = { @JoinColumn(name = "ownedTests") }
    )
    private List<User> members;

    private Double maxBall;

    private Integer seconds;
}
