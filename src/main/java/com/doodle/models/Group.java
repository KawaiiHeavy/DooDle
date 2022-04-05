package com.doodle.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Groups")
@Table(name = "group", schema = "public")
public class Group {

    @Id
    @GeneratedValue()
    @Column(name="id")
    private UUID id;

    private String title;

    @ManyToOne
    private User groupLeader;

    @ManyToMany
    private List<User> members;

}
