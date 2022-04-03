package com.doodle.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Users")
@Table(name="user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "nickname"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })}, schema = "public")
public class User {

    @Id
    @GeneratedValue()
    @Column(name="id")
    private UUID id;

    private String nickname;

    private String email;

    private String password;

    private String phone;

    @ManyToMany(mappedBy = "members")
    private List<Test> ownedTests;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String nickname, String email, String password){
        super();
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}
