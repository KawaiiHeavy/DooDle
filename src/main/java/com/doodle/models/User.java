package com.doodle.models;

import lombok.*;

import javax.persistence.*;
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
                "name"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
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
    @GeneratedValue
    private UUID id;

    private String name;

    private String email;

    private String password;

}
