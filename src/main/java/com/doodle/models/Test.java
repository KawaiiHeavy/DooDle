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

    private String title;

    @ManyToOne
    @JoinColumn(name = "creator")
    private User creator;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "members")
    private List<User> members;

    private Double maxBall;

    private Integer seconds;

}
