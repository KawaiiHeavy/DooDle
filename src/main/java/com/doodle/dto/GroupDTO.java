package com.doodle.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public enum GroupDTO {;

    private interface Id { @NotBlank UUID getId(); }
    private interface Title { @NotBlank String getTitle(); }
    private interface GroupLeader { @NotBlank UserDTO.Read getGroupLeader(); }

    @Value
    public static class Create implements Title, GroupLeader {
        String title;
        UserDTO.Read groupLeader;
    }

    @Value
    public static class Read implements Id, Title, GroupLeader {
        UUID id;
        String title;
        UserDTO.Read groupLeader;
    }

}
