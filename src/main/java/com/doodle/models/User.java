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
@Entity(name = "Users")
@Table(name="user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "nickname"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })}, schema = "public")
public class User {

    public static enum UserRole {
        ADMIN, STUDENT, TRAINER, USER;
        public static UserRole getById(String id){
            for(UserRole e : values()) {
                if(e.name().equalsIgnoreCase(id)) return e;
            }
            return USER;
        }
    }

    @Id
    @GeneratedValue()
    @Column(name="id")
    private UUID id;

    private String nickname;

    private String email;

    private String password;

    private String phone;

    private UserRole userRole;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private List<Test> ownedTests;

    public UUID getId() {return id;}
}
