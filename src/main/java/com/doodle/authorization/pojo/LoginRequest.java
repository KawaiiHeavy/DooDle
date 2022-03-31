package com.doodle.authorization.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String nickname;
    private String password;

}
