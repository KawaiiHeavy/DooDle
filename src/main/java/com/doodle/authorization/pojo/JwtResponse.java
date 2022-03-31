package com.doodle.authorization.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private UUID id;
    private String nickname;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, UUID id, String nickname, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.roles = roles;
    }
}
