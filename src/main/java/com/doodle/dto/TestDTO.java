package com.doodle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
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

    private interface PassedQuestions {
        @NotBlank Set<QuestionDTO.Passed> getQuestions();
    }

    private interface CreatedQuestions {
        @NotBlank Set<QuestionDTO.Create> getQuestions();
    }

    @Data
    @AllArgsConstructor
    public static class Create implements Title, Creator, Seconds, CreatedQuestions {
        String title;
        UserDTO.Read creator;
        Integer seconds;
        Double maxBall;
        Set<QuestionDTO.Create> questions;
    }

    @Data
    @AllArgsConstructor
    public static class Read implements Id, Title, Creator, MaxBall, Seconds, CreatedQuestions {
        UUID id;
        String title;
        UserDTO.Read creator;
        Double maxBall;
        Integer seconds;
        Set<QuestionDTO.Create> questions;
    }

    @Data
    @AllArgsConstructor
    public static class Passed implements Id, Title, Creator, MaxBall, Seconds, PassedQuestions {
        UUID id;
        String title;
        UserDTO.Read creator;
        Double maxBall;
        Integer seconds;
        Set<QuestionDTO.Passed> questions;
    }
}