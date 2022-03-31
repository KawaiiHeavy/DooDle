package com.doodle.authorization.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignUpRequest {

    private String nickname;
    private String email;
    private Set<String> roles;
    private String password;

}
