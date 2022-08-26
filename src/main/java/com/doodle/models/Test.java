package com.doodle.models;

import lombok.*;
import org.hibernate.engine.internal.Cascade;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Test {

    @Id
    @GeneratedValue()
    @Column(name="id")
    private UUID id;

    @Column(nullable = false)
    private String title;

    @CreatedBy
    @ManyToOne()
    private User creator;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> members;

    private Double maxBall;

    private Integer seconds;

}
