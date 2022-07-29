package com.doodle.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public enum UserDTO {
    ;

    private interface Id {
        @NotBlank UUID getId();
    }

    private interface Nickname {
        @NotBlank String getNickname();
    }

    private interface Password {
        @NotBlank String getPassword();
    }

    private interface Email {
        @NotBlank String getEmail();
    }

    private interface Phone {
        String getPhone();
    }

    private interface Roles {
        @NotBlank Set<RoleDTO> getRoles();
    }

    private interface OwnedTests {
        List<TestDTO> getOwnedTests();
    }

    @Value
    public static class Create implements Nickname, Password, Email, Roles {
        String nickname;
        String password;
        String email;
        Set<RoleDTO> roles;
    }

    @Value
    public static class Read implements Id, Nickname, Password, Roles {
        UUID id;
        String nickname;
        String password;
        Set<RoleDTO> roles;
    }

}
