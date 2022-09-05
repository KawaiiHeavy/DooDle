package com.doodle.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public enum TestDTO {;

    private interface Id {
        @NotBlank UUID getId();
    }

    private interface Title {
        @NotBlank String getTitle();
    }

    private interface Creator {
        @NotBlank UserDTO.Read getCreator();
    }

    private interface MaxBall {
        @NotBlank Double getMaxBall();
    }

    private interface Seconds {
        @NotBlank Integer getSeconds();
    }

    private interface Questions {
        @NotBlank Set<QuestionDTO.Create> getQuestions();
    }

    @Value
    public static class Create implements Title, Creator, MaxBall, Seconds, Questions {
        String title;
        UserDTO.Read creator;
        Double maxBall;
        Integer seconds;
        Set<QuestionDTO.Create> questions;
    }

    @Value
    public static class Read implements Id, Title, Creator, MaxBall, Seconds, Questions {
        UUID id;
        String title;
        UserDTO.Read creator;
        Double maxBall;
        Integer seconds;
        Set<QuestionDTO.Create> questions;
    }

}