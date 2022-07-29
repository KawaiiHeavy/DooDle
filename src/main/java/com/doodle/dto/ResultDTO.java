package com.doodle.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public enum ResultDTO {;

    private interface Id { @NotBlank UUID getId(); }
    private interface Test { @NotBlank TestDTO.Read getTest(); }
    private interface Participant { @NotBlank UserDTO.Read getParticipant(); }
    private interface Score { @NotBlank Double getScore(); }

    @Value
    public static class Create implements Test, Participant, Score {
        TestDTO.Read test;
        UserDTO.Read participant;
        Double score;
    }

    @Value
    public static class Read implements Id, Test, Participant, Score {
        UUID id;
        TestDTO.Read test;
        UserDTO.Read participant;
        Double score;
    }

}
