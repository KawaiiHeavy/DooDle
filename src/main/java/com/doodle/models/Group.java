package com.doodle.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "groups")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Group {

    @Id
    @GeneratedValue()
    private UUID id;

    @Column(nullable = false)
    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    private User groupLeader;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<User> members;

}
